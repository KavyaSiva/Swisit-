/**....*/
package com.stackroute.swisit.usersearchservice.domain;

/*-----Importing Libraries-----*/
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import javax.validation.constraints.NotNull;

/*-------IndicatorOf Node Domain Class--------*/

@RelationshipEntity(type="indicatorOf")
public class IndicatorOf {


    /*-------IndicatorOf Class Properties------*/
    @GraphId @NotNull
    Long id;
    @StartNode
    @NotEmpty
    Term term;
    @EndNode
    @NotEmpty
    Intent intent;
    @NotNull
    float weight;

    /*--------Default IndicatorOf Class Constructors--------*/
    public IndicatorOf() { }

    /*--------Parameterized IndicatorOf Class Constructor-------*/
    public IndicatorOf(Term term, Intent intent, float weight) {
        this.term = term;
        this.intent = intent;
        this.weight = weight;
    }

    /*------------Setter and Getter methods for Properties-----------*/
    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = Float.parseFloat(weight);
    }
}