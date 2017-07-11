package com.stackroute.swisit.searcher.controllertest;

	
	import org.junit.Assert;
	import org.junit.Before;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.mockito.InjectMocks;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
	import org.springframework.boot.test.mock.mockito.MockBean;
	import org.springframework.context.MessageSource;
	import org.springframework.context.i18n.LocaleContextHolder;
	import org.springframework.test.context.ContextConfiguration;
	import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
	import org.springframework.test.context.web.WebAppConfiguration;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
	import org.springframework.test.web.servlet.setup.MockMvcBuilders;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.context.WebApplicationContext;

import com.stackroute.swisit.searcher.controller.SearchController;
import com.stackroute.swisit.searcher.domain.SearcherResponse;
	import com.stackroute.swisit.searcher.domain.SearcherJob;
	import com.stackroute.swisit.searcher.hateoes.HateoesAssembler;
	import com.stackroute.swisit.searcher.intialconsumer.IntialConsumerImpl;
	import com.stackroute.swisit.searcher.intialproducer.IntialProducerImpl;
	import com.stackroute.swisit.searcher.loadbalancing.LoadBalancing;
	import com.stackroute.swisit.searcher.repository.SearcherJobRepository;
	import com.stackroute.swisit.searcher.repository.SearcherResultRepository;
	import com.stackroute.swisit.searcher.searcherservice.SearchService;
	import com.stackroute.swisit.searcher.searcherservice.SearchServiceImpl;
	import java.util.*;
	import javax.naming.directory.SearchResult;
	import static junit.framework.TestCase.assertEquals;
	import static org.mockito.Mockito.when;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
	import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
	@RunWith(SpringJUnit4ClassRunner.class)
	@WebAppConfiguration
	@ContextConfiguration(classes = SearchController.class)
	@WebMvcTest(controllers=SearchController.class)
	public class SearcherControllerTest{
	   
	   @Autowired
	   private MockMvc mockMvc;
	   
	   
	   @MockBean
	   private IntialProducerImpl intialProducerImpl;
	   
	   @MockBean
	   private IntialConsumerImpl intialConsumerImpl;
	   
	   @MockBean
	   private SearchServiceImpl searchService;
	   @Autowired
	   private WebApplicationContext webApplicationContext;
	   @MockBean
	   private SearcherJobRepository searcherJobRepository;
	   @MockBean
	   private SearcherResultRepository searcherResultRepository; 
	   @MockBean
	   private SearcherResponse searchResponse;
	   
	   @MockBean
	   private SearcherJob searcherJob;
	   
	   @MockBean
	   private LoadBalancing loadBalancing;
	   
	   
	   @MockBean
	   private SearchResult searcherResult;
	   @MockBean
	   private HateoesAssembler hateoasAssembler;
	   @MockBean
	   private MessageSource messageSource;
	   @InjectMocks
	   private SearchController searchController;
	   
	  
	        @Before
	        public void setUp() {
	            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	        }
	        @Test
	        public void saveSearcherJob() throws Exception
	        {
	            SearcherJob sj = new SearcherJob();
	            List<String> list=new ArrayList<String>();
	            list.add("class");
	            list.add("interface");
	            //sj.setConcept(list);
	            sj.setDomain("java");
	            sj.setConcept(list);
	            sj.setSitesearch("none");
	            sj.setResults("10");
	            
	            Iterable<SearcherJob> i=new ArrayList<>();
	            
	            when(searchService.saveAllSearcherResult(sj)).thenReturn((List) i);
	            mockMvc.perform(post("v1/api/swisit/searcher"))
	                    .andExpect(status().is(404));
	            
	        }
	        
	        
	}

