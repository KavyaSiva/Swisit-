/**..**/
package com.stackroute.swisit.usersearchservice.domain;


/*-----Importing Libraries-----*/


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/*--------ConceptResult node Domain Class--------*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptResult {

    /*-------ConceptResult Class Properties------*/

    @NotEmpty
    @JsonProperty("conceptid")
    String nodeid;
    @NotEmpty
    @JsonProperty("name")
    private String name;

    /*-------Default Constructor of ConceptResult Domain Class-------*/
    public ConceptResult() {}

    /*-------Parameterized Constructor of ConceptResult Domain Class-------*/
    public ConceptResult(String nodeid,String name) {
            this.nodeid = nodeid;
            this.name = name;
    }

    /*----------Overriding toString method to print ConceptResult----------*/
    @Override
    public String toString() {
        return "ConceptResult{" +
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