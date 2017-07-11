package com.stackroute.swisit.searcher.kafkaserialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.swisit.searcher.domain.SearcherJob;

/* serializing the searcherJob bean */
public class QuerySerializer implements Serializer<SearcherJob>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	/* serializing message of SearcherJob */
	@Override
	public byte[] serialize(String topic, SearcherJob data) {
		// TODO Auto-generated method stub
		byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(data).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;}
	

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
