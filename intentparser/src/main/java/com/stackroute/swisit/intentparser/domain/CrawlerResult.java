package com.stackroute.swisit.intentparser.domain;
/*--------Importing Libraries-------*/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;
/*--------Crawler Result Domain Class which is Input for Intent Parser Service--------*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrawlerResult {

    @NotEmpty
    @JsonProperty("query")
    private String query;
    @NotEmpty
    @JsonProperty("link")
    private String link;
    @NotEmpty
    @JsonProperty("terms")
    private ArrayList<ContentSchema> terms;
    @NotEmpty
    @JsonProperty("title")
    private String title;
    @NotEmpty
    @JsonProperty("snippet")
    private String snippet;
    @NotNull
    @JsonProperty("lastindexedof")
    private Date lastindexedof;

    @JsonProperty("concept")
    private String concept;

    /*-----------Default Constructor of Crawler Result Class------------*/
    public CrawlerResult() { }

    /*----------Parameterized Constructor of Crawler Result Class---------*/
    public CrawlerResult(String query, String link, ArrayList<ContentSchema> terms, String title, String snippet,Date lastindexof,String concept) {
        this.query = query;
        this.link = link;
        this.terms = terms;
        this.title = title;
        this.snippet = snippet;
        this.lastindexedof = lastindexof;
        this.concept = concept;
    }

    /*---------Overridden ToString Method of Crawler Result Class--------*/

    @Override
    public String toString() {
        return "CrawlerResult{" +
                "query='" + query + '\'' +
                ", link='" + link + '\'' +
                ", terms=" + terms +
                ", title='" + title + '\'' +
                ", snippet='" + snippet + '\'' +
                ", lastindexedof=" + lastindexedof +
                ", concept='" + concept + '\'' +
                '}';
    }

    /*-------------Getter and Setter Methods Of Properties------------*/
    public Date getLastindexedof() {
		return lastindexedof;
	}

	public void setLastindexedof(Date lastindexedof) {
		this.lastindexedof = lastindexedof;
	}

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<ContentSchema> getTerms() {
		return terms;
	}

	public void setTerms(ArrayList<ContentSchema> terms) {
		this.terms = terms;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
}