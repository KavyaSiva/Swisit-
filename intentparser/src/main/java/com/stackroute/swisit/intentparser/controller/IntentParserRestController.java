/*******This Class is used for Testing the Service only,
 * will finally be removed from the Final product******/

package com.stackroute.swisit.intentparser.controller;

/*--------- Importing Libraries---------------*/
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stackroute.swisit.intentparser.assembler.HeteoasLinkAssembler;
import com.stackroute.swisit.intentparser.domain.CrawlerResult;
import com.stackroute.swisit.intentparser.domain.Intent;
import com.stackroute.swisit.intentparser.domain.Term;
import com.stackroute.swisit.intentparser.domain.IntentParserResult;
import com.stackroute.swisit.intentparser.service.IntentParseAlgo;
import com.stackroute.swisit.intentparser.service.IntentParserService;
import com.stackroute.swisit.intentparser.subscriber.SubscriberImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.*;

/*----------------------Rest API Controller Class------------------------*/
@RestController
@RequestMapping(value="/v1/api/parse/")
@Api(value="IntentParserRestController", description="Operations pertaining to the IntentParserService")
public class IntentParserRestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/*----------Autowired Instances of Classes----------*/
	@Autowired
	IntentParserService intentParserService;
	@Autowired
	IntentParseAlgo intentParseAlgo;
	@Autowired
	HeteoasLinkAssembler heteoasLinkAssembler;
	@Autowired
	SubscriberImpl subscriberImpl;
	@Autowired
	private MessageSource messageSource;

	/*----------------Swagger API Operations-----------------*/
	@ApiOperation(value="subscribe",response = CrawlerResult.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Crawler"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)
	/*---------------REST Controller get Input from Kafka-----------------*/
	@RequestMapping(value="subscribe" , method=RequestMethod.GET)
	public ResponseEntity<Iterable> subscribe(){
		Iterable<CrawlerResult> l=subscriberImpl.receivingMessage("tointent");
		// CrawlerResult cr[]=new CrawlerResult[l.size()];
		//l.toArray(cr);
		for(CrawlerResult lr:l){
			logger.debug("link is "+lr.getLink());
			logger.debug("query is "+lr.getQuery());
			logger.debug("snippet is "+lr.getSnippet());
			logger.debug("title is  "+lr.getTitle());
			logger.debug("date is "+lr.getLastindexedof());

		}
		return new ResponseEntity<Iterable>(l, HttpStatus.OK);
	}

	/*----------------Swagger API Operations-----------------*/
	@ApiOperation(value="fetchNeoData",response = CrawlerResult.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Crawler"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)
	/*----------- REST Controller To Show Data From Neo4j Database------------*/
	@RequestMapping(value = "/fetch", method= RequestMethod.POST)
	public ResponseEntity<Iterable> fetchNeoData(){

		List<Intent> intentsList = intentParserService.getAllIntents();
		List<Term> termsList = intentParserService.getAllTerms();
		Iterable<Map<String,Object>> indicatorOfs = intentParserService.getAllIndicator();

		Iterable<Map<String,Object>> bothRelationships = intentParserService.getBothRelationships();
		Iterable<Map<String,Object>> allRelationships = intentParserService.getAllRelationships();

		Iterable<Map<String,String>> getAllIntentRelationships = intentParserService.getAllTermsRelationOfIntent("example");
		return new ResponseEntity<Iterable>(getAllIntentRelationships, HttpStatus.OK);
	}

	/*----------------Swagger API Operations-----------------*/
	@ApiOperation(value="Calculate Confidence Score",response = CrawlerResult.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Crawler"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)
	/*----------- REST Controller To get IntentParser Response-----------*/
	@RequestMapping(value = "", method= RequestMethod.POST)
	public ResponseEntity<List> calculateConfidenceScore() throws JsonParseException, JsonMappingException, IOException{

		List<IntentParserResult> result=null;

		/*------Locale which identify a specific language and geographic region, used for Internationalization-----*/
		Locale locale = LocaleContextHolder.getLocale();

		/*------Try Catch block for Handling Exceptions-----*/
		try{
			/*-------getting input from Kafka subscriber------*/
			Iterable<CrawlerResult> intentInput=subscriberImpl.receivingMessage("tointent");
			if(intentInput==null){
				String message = messageSource.getMessage ("user.excep.data", null, locale );
				return new ResponseEntity(message,HttpStatus.OK);
			}

			for(CrawlerResult lr:intentInput){
				logger.info("link is "+lr.getLink());
				logger.info("query is "+lr.getQuery());
				logger.info("content schema is "+lr.getTerms().get(0).getWord()+"---"+lr.getTerms().get(0).getIntensity());
				logger.info("snippet is "+lr.getSnippet());
				logger.info("title is  "+lr.getTitle());
				logger.info("date is "+lr.getLastindexedof());
			}

			/*-------Resulted List from Intent Parser Algo-------*/
			result=(List<IntentParserResult>)intentParseAlgo.calculateConfidence(intentInput);

			/*-------Hateoas Link Assembling to Response-------*/
			List<IntentParserResult> intentParserResult=heteoasLinkAssembler.calculateConfidence(result);
			return new ResponseEntity<List>(intentParserResult, HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<List>(result, HttpStatus.OK);
	}
}
