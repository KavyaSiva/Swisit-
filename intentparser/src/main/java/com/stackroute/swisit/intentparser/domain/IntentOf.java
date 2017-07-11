/*******This Class is used for Testing the Service only,
 * will finally be removed from the Final product******/

package com.stackroute.swisit.intentparser.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="IntentOf")
public class IntentOf {
    @GraphId @NotNull
    Long id;
    @StartNode
    @NotEmpty
    Intent intent;
    @EndNode @NotEmpty
    Intent domain;
    @NotNull
    float weight;

    public IntentOf() {    }

    public IntentOf(Long id, Intent intent, Intent domain, float weight) {
        this.id = id;
        this.intent = intent;
        this.domain = domain;
        this.weight = weight;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getDomain() {
        return domain;
    }

    public void setDomain(Intent domain) {
        this.domain = domain;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
