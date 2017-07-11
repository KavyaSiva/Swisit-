package com.stackroute.swisit.crawler.domain;

/*---------------Importing Libraries----------------*/
import javax.validation.constraints.NotNull;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*-----------Intensity domain class to generate and set the intensity for terms-------------*/
@JsonIgnoreProperties(ignoreUnknown=true)
public class Intensity {
	
	/*-------------Private variables of domain class------------*/
	
	@JsonProperty("title")
	@NotNull
	private String title;
	
	@JsonProperty("intensity")
	@NotNull
	private String intensity;
	
	/*-----------Default Constructor of Intensity Class------------*/
	public Intensity() {
		super();
	}

	/*----------Parameterized Constructor of Intensity Class---------*/
	public Intensity(String title, String intensity) {
		super();
		this.title = title;
		this.intensity = intensity;
	}

	/*------------- Getters and setters for fields -----------*/
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

}
