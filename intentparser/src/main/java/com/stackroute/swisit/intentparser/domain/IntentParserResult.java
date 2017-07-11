package com.stackroute.swisit.intentparser.domain;
/*-------Importing Libraries-------*/
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.ResourceSupport;
/*---------Intent Parser Result Class -------*/
public class IntentParserResult extends ResourceSupport {
    /*--------Intent Parser Result Properties-------*/
	@NotEmpty 
    String url;
	@NotEmpty
    String intent;
	@NotNull
    float confidenceScore;
    @NotNull
    String concept;

    /*-------Default Constructor of Intent Parser Result Class------*/
    public IntentParserResult() { }

    /*--------Parameterized Constructor of Intent Parser Result Class---------*/
    public IntentParserResult(String url, String intent, float confidenceScore,String concept) {
        this.url = url;
        this.intent = intent;
        this.confidenceScore = confidenceScore;
        this.concept = concept;
    }
    /*----------Overriding toString method to print IntentParserResult----------*/
    @Override
    public String toString() {
        return "IntentParserResult{" +
                "url='" + url + '\'' +
                ", intent='" + intent + '\'' +
                ", confidenceScore=" + confidenceScore +
                ", concept='" + concept + '\'' +
                '}';
    }

    /*------------Setter and Getter methods for Properties-----------*/
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public float getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(float confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getConcept() { return concept; }

    public void setConcept(String concept) { this.concept = concept; }
}
