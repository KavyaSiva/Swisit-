package com.stackroute.swisit.intentparser.domain;
/*--------Importing Libraries-------*/
import javax.validation.constraints.NotNull;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
/*--------Term node Domain Class--------*/
@NodeEntity
public class Term {
    @GraphId @NotNull
    private Long id;
    @NotNull
    private String nodeid;
    @NotNull
    private String name;

    /*-------Default Constructor of Term Domain Class-------*/
    public Term() {}

    /*-------Parameterized Constructor of Term Domain Class-------*/
    public Term(String nodeid, String name) {
        this.name = name;
        this.nodeid = nodeid;
    }
    /*--------Overriding toString method for Term Bean class-------*/
    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", nodeid='" + nodeid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /*------------Setter and Getter methods for Properties-----------*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

}
