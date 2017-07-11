package com.stackroute.swisit.documentparser.domain;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;

/*--------WordChecker Class --------*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class WordChecker {
	@JsonProperty("word")
	private String word;

	
	
	
	public WordChecker(String word) {
		super();
		this.word = word;
	}

	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		word = word;
	}
	

}
