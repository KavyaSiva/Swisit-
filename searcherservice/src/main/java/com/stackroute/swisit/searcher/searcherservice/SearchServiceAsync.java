package com.stackroute.swisit.searcher.searcherservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.swisit.searcher.domain.SearcherResponse;
import com.stackroute.swisit.searcher.domain.SearcherResult;
import com.stackroute.swisit.searcher.exception.SearcherServiceException;
import com.stackroute.swisit.searcher.messageservice.MessageService;
import com.stackroute.swisit.searcher.messageservice.MessageServiceImpl;
import com.stackroute.swisit.searcher.repository.SearcherResultRepository;
@Service
public class SearchServiceAsync {
	
	@Autowired
	MessageService kafkaconfig;
	@Autowired
	private SearcherResultRepository searcherResultRepository;
	
	SearcherResponse searchResponse = new SearcherResponse();
	
	SearcherResult searcherResult = new SearcherResult();
	
	List<SearcherResult> searcherResultList=new ArrayList<SearcherResult>();
	
	private static final Logger logger = LoggerFactory.getLogger(SearchService.class);
	
	/* Returns SearcherResult as Async to the SearchServiceImpl class */
	@Async
	public CompletableFuture<SearcherResponse> getSearchResult(String finalUrl) throws JsonProcessingException, InterruptedException, ExecutionException {
		 
		/* RestTemplate is uesd to get object from the url */
		RestTemplate restTemplate = new RestTemplate();
		
		/* executor is used to start the FutureTask */
		ExecutorService executor = Executors.newFixedThreadPool(2);
		/* Created futureTask for SearchResponse 
		 * call() method is used to request the Google Api to get result and stored 
		 * in the futureTask object*/
		FutureTask<SearcherResponse> futureTask = new FutureTask<SearcherResponse>(new Callable<SearcherResponse>(){

			@Override
			public SearcherResponse call() throws Exception {
				/* RestTemplate is a class that contains getForObject method 
		   		to get the value from the Google Api and stored as object */ 
				return restTemplate.getForObject(finalUrl,SearcherResponse.class);
			}
			
		});
		/* method to execute the futureTask */
		executor.execute(futureTask);
		try {
			searchResponse=futureTask.get();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
		return CompletableFuture.completedFuture(searchResponse);
		
	}

}


