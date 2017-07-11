package com.stackroute.swisit.usersearchservice.domain;

/*--------Importing Libraries-------*/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/*----------Relationship Domain Class----------*/
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class RelationShipOf {

    /*-------Relationship Class Properties------*/

    @NotEmpty
    @JsonProperty("termname")
    private String termname;
    @NotEmpty
    @JsonProperty("intentname")
    private String intentname;

    @NotEmpty
    @JsonProperty("relname")
    private String relname;

    @NotNull
    @JsonProperty("weight")
    private float weight;

    /*-----------Default Constructor of RelationsShip Class------------*/
    public RelationShipOf() {
    }

    /*----------Parameterized Constructor of RelationsShip Class---------*/
    public RelationShipOf(String termname, String intentname, String relname, float weight) {
        this.termname = termname;
        this.intentname = intentname;
        this.relname = relname;
        this.weight = weight;
    }

    /*---------Overridden ToString Method of RelationsShip Class--------*/
    @Override
    public String toString() {
        return "RelationShipOf{" +
                "termname='" + termname + '\'' +
                ", intentname='" + intentname + '\'' +
                ", relname='" + relname + '\'' +
                ", weight=" + weight +
                '}';
    }

    /*-------------Getter and Setter Methods Of Properties------------*/

    public String getTermname() {
        return termname;
    }

    public void setTermname(String termname) {
        this.termname = termname;
    }

    public String getIntentname() {
        return intentname;
    }

    public void setIntentname(String intentname) {
        this.intentname = intentname;
    }

    public String getRelname() {
        return relname;
    }

    public void setRelname(String relname) {
        this.relname = relname;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}