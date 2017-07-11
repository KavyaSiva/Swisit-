package com.stackroute.swisit.intentparser.service;
/*-------Importing Libraries------*/
import com.stackroute.swisit.intentparser.domain.*;
import com.stackroute.swisit.intentparser.exception.ConfidenceScoreNotCalculatedException;
import com.stackroute.swisit.intentparser.repository.DocToConcept;
import com.stackroute.swisit.intentparser.repository.IntentRepository;
import com.stackroute.swisit.intentparser.repository.RelationshipRepository;
import com.stackroute.swisit.intentparser.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/*-------Implementation of IntentParseAlgo Interface class------*/
@Service
public class IntentParseAlgoImpl implements IntentParseAlgo {

    /*-------Autowired Repositories-------*/
	@Autowired
    IntentRepository intentRepository;
	@Autowired
    RelationshipRepository relationshipRepository;
	@Autowired
	TermRepository termRepository ;
	@Autowired
	DocToConcept docToConcept;
    /*------------CalculateConfidence method for getting List of IntentParserResult-----------*/
    @Override
    public ArrayList<IntentParserResult> calculateConfidence(Iterable<CrawlerResult> intentInput){
    	List<Intent> intentsList = intentRepository.findIntents();
        ArrayList<IntentParserResult> intentParserResultList = new ArrayList<IntentParserResult>();
        for(CrawlerResult intentParserInput : intentInput){
            //intentParserResultList.addAll(calculateConfidenceScore(intentParserInput,intentsList));
        }
        Collections.sort(intentParserResultList, new Comparator<IntentParserResult>() {
            @Override
            public int compare(IntentParserResult intentParserResult1, IntentParserResult intentParserResult2) {
                return (int)(intentParserResult1.getConfidenceScore()-intentParserResult2.getConfidenceScore());
            }
        });
        return intentParserResultList;
    }

    /*-----------CalculateConfidenceScore Method to calculate confidence score for each
                 IntentParserResult bean and Creting an Object of IntentParserResult and
                 also saves the result in Neo4jDatabse. It returns the arraylist of
                 IntentParseResult for Each Intent-----------*/
	@Override
	public ArrayList<IntentParserResult> calculateConfidenceScore(CrawlerResult intentParserInput/*,List<Intent> intentList*/){
		ArrayList<IntentParserResult> results=new ArrayList<IntentParserResult>();
		List<Intent> intentList = intentRepository.findIntents();
		for (Intent intent : intentList) {
			List<Map<String, String>> relationshipList = relationshipRepository.getAllTermsRelationOfIntent(intent.getName());
			/*exception handling*/
				try {
					if (relationshipList == null) {
					throw new ConfidenceScoreNotCalculatedException("Empty data in database");
					}
				} catch (ConfidenceScoreNotCalculatedException e) {
					e.printStackTrace();
				}
			
			/*exception handling*/
			ArrayList<ContentSchema> contentSchemas = intentParserInput.getTerms();
			ArrayList<Relationships> relationsList = new ArrayList<Relationships>();
			System.out.print("weight problem here ------- ");
			for (Map<String, String> map : relationshipList) {
				Relationships relationships = new Relationships();
				relationships.setIntentName(map.get("intentName"));
				relationships.setTermName(map.get("termName"));
				relationships.setRelName(map.get("relName"));
				relationships.setWeight(Float.parseFloat(map.get("weight")));
				relationsList.add(relationships);
			}
			float indicator = 0f, counterIndicator = 0f;
			for (ContentSchema contentSchema : contentSchemas) {
				for (Relationships relationships : relationsList) {
					if (contentSchema.getWord() == null) { continue; }
					if (contentSchema.getWord().equalsIgnoreCase(relationships.getTermName())) {
						if (relationships.getRelName().equalsIgnoreCase("indicatorOf")) {
							indicator += (contentSchema.getIntensity() * relationships.getWeight());
						}
						if (relationships.getRelName().equalsIgnoreCase("counterIndicatorOf")) {
							counterIndicator += (contentSchema.getIntensity() * relationships.getWeight());
						}
					}
				}
			}
			float confidenceScore = indicator - counterIndicator;
			IntentParserResult intentParserResult = new IntentParserResult(intentParserInput.getLink(), intent.getName(), confidenceScore, intentParserInput.getConcept());
			System.out.println(intentParserResult.toString());
			try{
				intentRepository.createDocumentNode(intentParserResult.getUrl());
			}catch(Exception e){ }
			docToConcept.createDocToConceptRels(intentParserResult.getUrl(),intentParserResult.getIntent(),intentParserResult.getConfidenceScore(),intentParserResult.getConcept(),intentParserInput.getSnippet());
			results.add(intentParserResult);
		}
		return results;
	}

	public void get(CrawlerResult cr){
		System.out.println(cr);
	}
}

