package com.stackroute.swisit.intentparser.domain;
/*--------Importing Libraries--------*/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/*----------Relationship Domain Class----------*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Relationships {
    /*-------Relationship Class Properties------*/
    @NotEmpty
    @JsonProperty("termName")
    String termName;
    @NotEmpty
    @JsonProperty("intentName")
    String intentName;
   @NotEmpty
    @JsonProperty("relName")
    String relName;
    @NotEmpty
    @JsonProperty("weight")
    float weight;

    /*-------Default Constructor Of Relationships Class------*/
    public Relationships() {}

    /*---------Parameterized Constructor of Relationships Class--------*/
    public Relationships(String termName, String intentName, String relName, float weight) {
        this.termName = termName;
        this.intentName = intentName;
        this.relName = relName;
        this.weight = weight;
    }

    /*------------Setter and Getter methods for Properties-----------*/
    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
