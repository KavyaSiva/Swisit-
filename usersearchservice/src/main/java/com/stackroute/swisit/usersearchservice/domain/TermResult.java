/**..*/
package com.stackroute.swisit.usersearchservice.domain;

/*-----Importing Libraries-----*/


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/*--------ConceptResult node Domain Class--------*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class TermResult {

    /*-------TermResult Class Properties------*/

    @NotNull
    private Long id;
    @NotNull
    String nodeid;
    @NotEmpty
    private String name;

    /*-------Default Constructor of TermResult Domain Class-------*/
    public TermResult() {
    }

    /*-------Parameterized Constructor of TermResult Domain Class-------*/
    public TermResult(String name) {
        this.nodeid = nodeid;
        this.name = name;

    }

    /*----------Overriding toString method to print TermResult----------*/

    @Override
    public String toString() {
        return "TermResult{" +
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



