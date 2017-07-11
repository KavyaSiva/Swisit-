package com.stackroute.swisit.usersearchservice.domain;
/*--------Importing Libraries-------*/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;

/*--------User Input Domain Class which is Input for User Search Service--------*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSearchResult  extends ResourceSupport{


    /*-------User Search Result Class Properties------*/
    @NotEmpty
    @JsonProperty("url")
    private String url;
    @NotEmpty
    @JsonProperty("description")
    private String description;
    @NotNull
    @JsonProperty("confidenceScore")
    private float confidenceScore;

    /*-------Default Constructor of User Search Result Class------*/
    public UserSearchResult() { }

    /*--------Parameterized Constructor of User Search Result Class---------*/

    public UserSearchResult(String url, String description, float confidenceScore) {
        this.url = url;
        this.description = description;
        this.confidenceScore = confidenceScore;
    }

    /*----------Overriding toString method to print UserSearchResult----------*/
    @Override
    public String toString() {
        return "UserSearchResult{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", confidenceScore=" + confidenceScore +
                '}';
    }



    /*------------Setter and Getter methods for Properties-----------*/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(float confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}
