/**....*/
package com.stackroute.swisit.usersearchservice.domain;
/*--------Importing Libraries-------*/
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;

/*--------Term node Domain Class--------*/
@NodeEntity
public class Term {

    /*-------Term Class Properties------*/
    @NotEmpty
    private String name;

    /*-------Default Constructor of Term Domain Class-------*/
    public Term() {}

    /*-------Parameterized Constructor of Term Domain Class-------*/
    public Term(String name) {
        this.name = name;
    }


    /*------------Setter and Getter methods for Properties-----------*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}