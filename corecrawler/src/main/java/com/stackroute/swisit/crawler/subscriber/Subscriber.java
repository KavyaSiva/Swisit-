package com.stackroute.swisit.crawler.subscriber;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stackroute.swisit.crawler.domain.SearcherResult;

public interface Subscriber {
	public List<SearcherResult> receivingMessage(String string) throws JsonParseException, JsonMappingException, IOException;
}
