package com.stackroute.swisit.documentparser.domain;

/**
 * Created by user on 30/6/17.
 */
/*-------Importing Liberaries--------*/
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/*---------ContententSchema Domain Class--------*/
public class ContentSchema {

    @NotEmpty
    @JsonProperty("word")
    private String word;

    @NotEmpty
    @JsonProperty("intensity")
    private float intensity;

    /*-------ContentSchema Default Constructor-------*/
    public ContentSchema() { }

    /*-------ContentSchema Parameterized Constructor ------*/
    public ContentSchema(String word, float intensity) {
        this.word = word;
        this.intensity = intensity;
    }

    /*------------Setter and Getter methods for Properties-----------*/
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }
}