package com.stackroute.swisit.crawler.serialization;

/*------------- Importing Libraries -----------*/
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.swisit.crawler.domain.CrawlerResult;

/*
 * CrawlerSerializer class implements Serializer to
 * serialize object before sending result to
 * publisher
 * */
public class CrawlerSerializer implements Serializer<CrawlerResult>{

	/*-------------Overriding methods of serializer for kafka------------*/
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	/*-------- Serializing object to be published into kafka---------*/
	@Override
	public byte[] serialize(String topic, CrawlerResult data) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(data).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public void close() {

	}



}
