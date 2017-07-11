package com.stackroute.swisit.intentparser.domain;
/*-------Importing Libraries--------*/
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

/*-------Intent Node Domain Class--------*/
@NodeEntity
public class Intent {

    @GraphId @NotNull
    private Long id;
    @NotNull
    String nodeid;
    @NotEmpty
    String name;
    /*--------Default Intent Class Constructors--------*/
    public Intent(){}

    /*--------Parameterized Intent Class Constructor-------*/
    public Intent(String nodeid, String name){
        this.nodeid = nodeid;
        this.name = name;
    }

    /*--------IndicatorOf Relationship Set between Terms and Intent nodes--------*/
    @Relationship(type = "indicatorOf", direction = Relationship.INCOMING)
    private Set<Term> indicatorTerms;

    /*---------method to get Indicator Terms of an Intent---------*/
    public void indicatorOf(Term term) {
        if (indicatorTerms == null) {
            indicatorTerms = new HashSet<Term>();
        }
        indicatorTerms.add(term);
    }

    /*--------counterIndicatorOf Relationship Set between Terms and Intent nodes--------*/
    @Relationship(type = "counterIndicatorOf", direction = Relationship.INCOMING)
    private Set<Term> counterIndicatorTerms;

    /*---------method to get CounterIndicator Terms of an Intent---------*/
    public void counterIndicatorOf(Term term) {
        if (counterIndicatorTerms == null) {
            counterIndicatorTerms = new HashSet<Term>();
        }
        counterIndicatorTerms.add(term);
    }

    /*------------Setter and Getter methods for Properties-----------*/
    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
