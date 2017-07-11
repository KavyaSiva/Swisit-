package com.stackroute.swisit.searcher.messageservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.swisit.searcher.domain.SearcherResult;

public interface MessageService {
	public void publishMessage(String topic,SearcherResult message) throws JsonProcessingException;
	public void listenMessage(String topic);
}
