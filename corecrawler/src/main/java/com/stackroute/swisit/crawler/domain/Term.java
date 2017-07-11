package com.stackroute.swisit.crawler.domain;

import javax.validation.constraints.NotNull;

/*---------------Importing Libraries--------------*/
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/*------------Term node Domain Class------------*/
@NodeEntity
public class Term {

	/*-------------Private variables of domain class------------*/
	
	@GraphId 
	@NotNull
	private Long id;
	
	@NotNull
	private String name;
	
	/*-----------Default Constructor of Crawler Result Class------------*/
	public Term() {
		super();
	}

	/*----------Parameterized Constructor of Crawler Result Class---------*/
	public Term(String name) {
		this.name = name;
	}

	/*------------- Getters and setters for fields -----------*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}