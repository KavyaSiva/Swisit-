package com.stackroute.swisit.usersearchservice.domain;

/*--------Importing Libraries-------*/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
/*--------User Input Domain Class which is Input for User search Service--------*/
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class UserInput {


    /*-------UserInput Class Properties------*/
    @NotEmpty
    @JsonProperty("domain")
    private String domain;
    @NotEmpty
    @JsonProperty("concept")
    private String concept;
    @NotEmpty
    @JsonProperty("term")
    private String term;

    /*-----------Default Constructor of UserInput Class------------*/
    public UserInput() { }

    /*----------Parameterized Constructor of UserInput Class---------*/

    public UserInput(String domain, String concept, String term) {
        this.domain = domain;
        this.concept = concept;
        this.term = term;
    }


    /*---------Overridden ToString Method of UserInput Class--------*/
    @Override
    public String toString() {
        return "UserInput{" +
                "domain='" + domain + '\'' +
                ", concept='" + concept + '\'' +
                ", term='" + term + '\'' +
                '}';
    }

    /*-------------Getter and Setter Methods Of Properties------------*/

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}