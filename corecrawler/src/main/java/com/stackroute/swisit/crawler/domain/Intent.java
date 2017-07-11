package com.stackroute.swisit.crawler.domain;

/*---------------Importing Libraries--------------*/
import org.neo4j.ogm.annotation.*;

import java.util.*;

import javax.validation.constraints.NotNull;

/*------ Intent domain class to fetch terms from neo4j ------*/
@NodeEntity
public class Intent {
	
	/*-------------Private variables of domain class------------*/
	
    @GraphId
    @NotNull
    private Long id;
    
    @NotNull
    private String name;
    
    /*---------- Variable that maps the relationship from neo4j-----------------*/
    @Relationship(type = "indicatorOf", direction = Relationship.UNDIRECTED)
    private Set<Term> terms;

    /*-----------Default Constructor of Crawler Result Class------------*/
    public Intent(){
    	
    }
    
    /*----------Parameterized Constructor of Crawler Result Class---------*/
    public Intent(String name){
        this.name=name;
    }

    /*----------Method that adds the terms from neo4j into terms set-----------*/
    public void indicatorOf(Term term) {
        if (terms == null) {
            terms = new HashSet<>();
        }
        terms.add(term);
    }
    
    /*------------- Getters and setters for fields -----------*/
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}