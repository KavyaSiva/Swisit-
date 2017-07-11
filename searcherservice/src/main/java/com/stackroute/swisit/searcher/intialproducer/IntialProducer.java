package com.stackroute.swisit.searcher.intialproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.swisit.searcher.domain.SearcherJob;
/* kafka producer for searcherJob */
public interface IntialProducer {
	public void publishMessage(String topic,SearcherJob message) throws JsonProcessingException;
}
