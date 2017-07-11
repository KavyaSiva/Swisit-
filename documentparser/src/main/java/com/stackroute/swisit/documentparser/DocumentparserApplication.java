package com.stackroute.swisit.documentparser;
import java.text.ParseException;
/*--------- Importing Libraries --------*/
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.swisit.documentparser.domain.ContentSchema;
import com.stackroute.swisit.documentparser.domain.DocumentParserResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.stackroute.swisit.documentparser.domain.CrawlerResult;
import com.stackroute.swisit.documentparser.service.MasterParserService;
import com.stackroute.swisit.documentparser.service.MasterParserServiceImpl;
import com.stackroute.swisit.documentparser.service.ObjectMapperService;
import com.stackroute.swisit.documentparser.subscriber.KafkaSubscriberImpl;
import com.stackroute.swisit.documentparser.threadconsumer.KafkaConsumer;

@SpringBootApplication
public class DocumentparserApplication {
	
	public static void main(String[] args) throws JsonProcessingException, ParseException {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(DocumentparserApplication.class, args);
		//KafkaSubscriberImpl subscriberImpl = applicationContext.getBean(KafkaSubscriberImpl.class);
		//subscriberImpl.receiveMessage("tonewparser2");
		KafkaConsumer kakfaConsumer = applicationContext.getBean(KafkaConsumer.class);
		kakfaConsumer.consumeMessage("tonewparser6");
		
		//MasterParserServiceImpl masterParserServiceImpl = applicationContext.getBean(MasterParserServiceImpl.class);
		//Iterable<DocumentParserResult> documentParserResults = null;
//		try {
//			Iterable<DocumentParserResult> documentParserResults = masterParserServiceImpl.parseDocument();
//			for(DocumentParserResult documentParserResult : documentParserResults){
//				System.out.println(documentParserResult.getConcept());
//				System.out.println(documentParserResult.getLink());
//				System.out.println(documentParserResult.getQuery());
//				System.out.println(documentParserResult.getLastindexedof());
//				System.out.println(documentParserResult.getSnippet());
//				System.out.println(documentParserResult.getTitle());
//				for(ContentSchema cs:documentParserResult.getTerms()){
//					System.out.println("term : "+cs.getWord()+"         intensity : "+cs.getIntensity());
//				}
//				System.out.println(documentParserResult.getTerms().get(0).toString());
//			}
//		}catch (Exception e){e.printStackTrace();}
		//List<CrawlerResult> list =subscriberImpl.receiveMessage("tonewparser");
		//CrawlerResult crawlerResult[] = new CrawlerResult[list.size()];
		//list.toArray(crawlerResult);
		//System.out.println("hi this is "+crawlerResult);
		/*
		Document doc;
		for(CrawlerResult cr : crawlerResult){
		doc = Jsoup.parse(cr.getDocument());
		System.out.println(cr.getLink());
		System.out.println(cr.getQuery());
		System.out.println(cr.getSnippet());
		System.out.println(cr.getLastindexedof());
		}
		*/

	}
}
