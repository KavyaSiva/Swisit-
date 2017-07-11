package com.stackroute.swisit.searcher.domain;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;



@JsonIgnoreProperties(ignoreUnknown=true)
@Document
/* To Send the result for the searcherJob */
public class SearcherResult extends ResourceSupport implements Serializer<SearcherResult>{
	@JsonProperty("link")
	String url;
	@JsonProperty("title")
	String title;
	@JsonProperty("snippet")
	String description;
	@JsonProperty("concept")
	String concept;
	public String getConcept() {
		return concept;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}
	private String encoding="UTF-8";
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public byte[] serialize(String arg0, SearcherResult arg1) {
		// TODO Auto-generated method stub
		byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(arg1).getBytes();
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}
}
	
	
	
	
	
	


