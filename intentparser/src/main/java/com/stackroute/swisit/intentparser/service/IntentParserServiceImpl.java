/*******This Class is used for Testing the Service only,
 * will finally be removed from the Final product******/
package com.stackroute.swisit.intentparser.service;
/*---------Importing Libraries--------*/
import com.stackroute.swisit.intentparser.domain.Intent;
import com.stackroute.swisit.intentparser.domain.Term;
import com.stackroute.swisit.intentparser.repository.IntentRepository;
import com.stackroute.swisit.intentparser.repository.RelationshipRepository;
import com.stackroute.swisit.intentparser.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*---------Implementation Class for IntentParserService-------*/
@Service
public class IntentParserServiceImpl implements IntentParserService {

    /*---------Autowired Repositories Instances--------*/
    @Autowired
    IntentRepository intentRepository;
    @Autowired
    TermRepository termRepository;
    @Autowired
    RelationshipRepository relationshipRepository;

    /*----------Method to get All Intent Nodes From Repository----------*/
    @Override
    public List<Intent> getAllIntents() {
        return intentRepository.findIntents();
    }

    /*----------Method to get All Term Nodes From Repository---------*/
    @Override
    public List<Term> getAllTerms() {
        return termRepository.findTerms();
    }

    /*----------Method to get All Indicator Terms From Repository----------*/
    @Override
    public Iterable<Map<String,Object>> getAllIndicator() {
        return relationshipRepository.getAllIndicator();
    }

    /*----------Method to get Indicator and CounterIndicator Relationships from Repository---------*/
    @Override
    public Iterable<Map<String, Object>> getBothRelationships() { return relationshipRepository.getBothRelationships(); }

    /*--------Method to get all relationships between Term and Intent nodes--------*/
    @Override
    public Iterable<Map<String,Object>> getAllRelationships() {
        return relationshipRepository.getAllRelationships();
    }

    /*----------Method to get All the Term nodes Related to Intent Node --------*/
    @Override
    public Iterable<Map<String,String>> getAllTermsRelationOfIntent(String intentName) {
        return relationshipRepository.getAllTermsRelationOfIntent(intentName);
    }

}
