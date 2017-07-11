package com.stackroute.swisit.crawler.domain;
/*------------Importing Libraries-------------*/
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*---------ContententSchema Domain Class--------*/
@JsonIgnoreProperties(ignoreUnknown=true)
public class ContentSchema {

	/*-------------Private variables of domain class------------*/
	
	@JsonProperty("word")
	@NotNull
	private String word;

	@JsonProperty("intensity")
	@NotNull
	private String intensity;


	/*-------ContentSchema Default Constructor-------*/

	public ContentSchema() {
		super();
	}


	/*-------ContentSchema Parameterized Constructor ------*/

	public ContentSchema(String word, String intensity) {
		super();
		this.word = word;
		this.intensity = intensity;
	}

	/*------------- Getters and setters for fields -----------*/

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}
}
