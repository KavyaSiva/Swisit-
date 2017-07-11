package com.stackroute.swisit.documentparser.serialization;

/*------------ Importing Libraries-----------*/
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.swisit.documentparser.domain.CrawlerResult;

/*-- Class to deserialize the result received from crawler --*/
public class CrawlerDeserializer implements Deserializer<CrawlerResult>{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*-------Deserializing the data received from crawler service------*/
	@Override
	public CrawlerResult deserialize(String arg0, byte[] arg1) {
		ObjectMapper objectMapper=new ObjectMapper();
		CrawlerResult c=null;
		try{
			//logger.info(arg1.toString());
			c=objectMapper.readValue(arg1,CrawlerResult.class);
			}
		catch(Exception e){
			logger.info("hi this "+e);
		}
		return c;
	}
	
	/*-- Overriding configure method of Deserializer --*/
	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		
	}
	
	/*-- Overriding close method of Deserializer --*/
	@Override
	public void close() {
		
	}

}
