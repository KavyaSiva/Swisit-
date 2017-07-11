/*package com.stackroute.swisit.crawler.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.net.MediaType;
import com.stackroute.swisit.crawler.controller.CrawlerRestController;
import com.stackroute.swisit.crawler.domain.Intensity;
import com.stackroute.swisit.crawler.domain.SearcherResult;
import com.stackroute.swisit.crawler.service.KeywordScannerServiceImpl;
import com.stackroute.swisit.crawler.service.MasterScannerService;
import com.stackroute.swisit.crawler.subscriber.KafkaSubscriberImpl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import javax.validation.Validator;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes =SearcherResult.class, loader = AnnotationConfigContextLoader.class)
@WebMvcTest(controllers = CrawlerRestController.class)



public class CrawlerRestControllerTest
{
    private MockMvc mockMvc;
    @Autowired
    private ApplicationContext appCtx;
    @Autowired
   private WebApplicationContext webApplicationContext;

    @MockBean
    private KeywordScannerServiceImpl keywordScannerServiceImpl;
   @MockBean
   private MasterScannerService masterScannerService;
   @MockBean
    private KafkaSubscriberImpl subscriber;
   @MockBean
   private Validator validator;
   
   @Before
   public void setUp() {
       mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }
 
  @Test
  public void receiveMessage() throws Exception {

      String expectedstring="{"+"\"message\":\"Data received successfully\""+"}";
   
 mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/swisit/crawler/receiver"))
       .andExpect(status().isOk());
   
                                                 }
}*/