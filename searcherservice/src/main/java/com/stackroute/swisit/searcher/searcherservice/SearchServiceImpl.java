package com.stackroute.swisit.searcher.searcherservice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.swisit.searcher.domain.SearcherResponse;
import com.stackroute.swisit.searcher.domain.SavingSearcherResult;
import com.stackroute.swisit.searcher.domain.SearcherJob;
import com.stackroute.swisit.searcher.domain.SearcherResult;
import com.stackroute.swisit.searcher.exception.SearcherServiceException;
import com.stackroute.swisit.searcher.messageservice.MessageService;
import com.stackroute.swisit.searcher.messageservice.MessageServiceImpl;
import com.stackroute.swisit.searcher.publisher.Publisher;
import com.stackroute.swisit.searcher.repository.SearcherJobRepository;
import com.stackroute.swisit.searcher.repository.SearcherResultRepository;


@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearcherResultRepository searcherResultRepository;
	
	@Autowired
	private SearcherJobRepository searcherJobRepository;
	
	@Autowired
	Publisher kafkaconfig;
	
	SearcherJob searcherJob = new SearcherJob();
	SearcherResult searcherResult=new SearcherResult();
	List<SearcherResult> searcherResultList=new ArrayList<SearcherResult>();
	RestTemplate restTemplate = new RestTemplate();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* used to get the values from application.yml */
	@Value("${url}")
	String url;
	@Value("${key}")
	String key;
	String domain="";
	String url1;
	String url2;
	List<String> concept;
	List<LinkedHashMap<String,String>> engineid = new ArrayList<LinkedHashMap<String,String>>();
	/* To Make the Google Api Async */
	SearchServiceAsync SearchServiceAsync = new SearchServiceAsync();
	
	/* save the search result for the query into SearchResult class */
	@Override
	public List saveAllSearcherResult(SearcherJob searcherJob) throws JsonProcessingException, InterruptedException, ExecutionException {
		
		CompletableFuture<SearcherResponse> searcherResponse=null;
		MessageServiceImpl kafkaconfig = new MessageServiceImpl(); 
		List<SearcherResult> searcherResultList = new ArrayList<SearcherResult>();
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("./src/main/resources/common/googleEngine.json");
		List<LinkedHashMap<String, String>> endigeIdList;
		try {
			
			endigeIdList = (List<LinkedHashMap<String,String>>) mapper.readValue(file, ArrayList.class);
			searcherJob.setEngineId(endigeIdList);
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/* Get the data from the SearchJob class */
		try
		{
			if(searcherJob.getDomain()==null || searcherJob.getConcept()==null) {
				throw new SearcherServiceException("SearcherJob has no value");
				
			}
			else
			{
					domain = searcherJob.getDomain();
					concept = searcherJob.getConcept();
					for(int k=0;k<concept.size();k++)
					{
						System.out.println("first loop" +concept.get(k));
						String query = domain+" "+concept.get(k);	
						engineid = searcherJob.getEngineId();
						for(Map<String, String> map : engineid){
							url1=map.get(key);
							break;
						}
						SavingSearcherResult savingSearcherResult =  new SavingSearcherResult();
						
						for(int i=1;i<=11;i=i+10)
						{	
							System.out.println("inside second loop");
							url2 = query+"&start="+i+url1;
							String finalUrl = url+url2;
							searcherResponse = (CompletableFuture<SearcherResponse>) SearchServiceAsync.getSearchResult(finalUrl);						
								
							/* setting the query from the Google Api */
							savingSearcherResult.setQuery(searcherResponse.get().getQueries());
						
							/* set the values to SearcherResult class and send the object to
			   				crawlerService via Kafka */ 
							try
							{	
								for(SearcherResult searcherResultRef:searcherResponse.get().getS())
								{
									SearcherResult searcherResult =  new SearcherResult();
									searcherResult.setUrl(searcherResultRef.getUrl());
									searcherResult.setTitle(searcherResultRef.getTitle());
									searcherResult.setDescription(searcherResultRef.getDescription());
									searcherResult.setConcept(concept.get(k));
									searcherResultList.add(searcherResult);
									try {
										/* publish the searcherResult object to kafka */
										kafkaconfig.publishMessage("testcontrolfinal3", searcherResult);
									} 
									catch (JsonProcessingException e) {
										e.printStackTrace();
									}
									//						SearcherResult s;
									//						redisTemplate.opsForHash().put(searcherResult.getQuery(), searcherResult.getConcept(), searcherResultRef);
									//redisTemplate.opsForList("SearcherResult",searcherResult);
									//redisTemplate.opsForSet.add("SearcherResult",searcherResult);
									/* saving the searcherResult to mongoDB */
									//						System.out.println("before redis store");
									//						redisTemplate.opsForHash().put("SResult",searcherResult.getQuery(),searcherResult);
									//searcherResultRepository.save(searcherResult);
								}
								
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							
						}
						savingSearcherResult.setSearcherResults(searcherResultList);
						/* save SearcherResult to mongoDB */
						searcherResultRepository.save(savingSearcherResult);
						searcherResultList.clear();
						
					}
				}
			
		}
		catch(SearcherServiceException e)
		{
			logger.error("Exception "+e.getMessage());
		}
		
	return searcherResultRepository.findAll();
	}
	

	/* get all data from the SearchResult class */
	@Override
	public List getAllSearcherResult() {
		try
		{
			if(searcherResultRepository.findAll()==null)
			{
				throw new SearcherServiceException("No data available");
			}
			else if(domain==null) {
				throw new SearcherServiceException("Domain was not Found");
			}
			else
			{
				/* method to get all data from the database */
				searcherResultRepository.findAll();
			}
		}
		catch(SearcherServiceException e)
		{
			logger.error("Exception"+e.getMessage());
		}
	return searcherResultRepository.findAll();
	}



}
