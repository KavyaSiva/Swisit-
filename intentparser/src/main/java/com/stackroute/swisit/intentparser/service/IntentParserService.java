/*******This Class is used for Testing the Service only,
 * will finally be removed from the Final product******/
package com.stackroute.swisit.intentparser.service;

/*------Importing Libraries-------*/
import com.stackroute.swisit.intentparser.domain.Intent;
import com.stackroute.swisit.intentparser.domain.Term;

import java.util.List;
import java.util.Map;

/*---------IntentParserService Interface Class---------*/
public interface IntentParserService {

    /*-------Methods to Interact with  Database------*/
    List<Intent> getAllIntents();
    List<Term> getAllTerms();
    Iterable<Map<String,Object>> getAllIndicator();
    Iterable<Map<String,Object>> getBothRelationships();
    Iterable<Map<String,Object>> getAllRelationships();
    Iterable<Map<String,String>> getAllTermsRelationOfIntent(String intentName);
}
