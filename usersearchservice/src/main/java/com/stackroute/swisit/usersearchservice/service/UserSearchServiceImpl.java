package com.stackroute.swisit.usersearchservice.service;
/*-----------Importing Libraries------------*/
import com.stackroute.swisit.usersearchservice.domain.*;
import com.stackroute.swisit.usersearchservice.exception.NeoDataNotFetchedException;
import com.stackroute.swisit.usersearchservice.repository.UserSearchServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*-------Implementation of UserSearchService Interface class------*/
@Service
public class UserSearchServiceImpl implements UserSearchService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /*-------Autowired Repositories-------*/
    @Autowired
    UserSearchServiceRepository userSearchServiceRepository;


    UserInput userInput = new UserInput();

    ArrayList<UserSearchResult> userSearchResults = new ArrayList<UserSearchResult>();



    /*------------fetchConcept method for getting List of ConceptResult-----------*/
    @Override
    public List<String> fetchConcept() {

        List<String> conceptResult = userSearchServiceRepository.findConcepts();

        //System.out.println(conceptResult.size());
        return conceptResult;
    }



    /*------------fetchTerm method for getting List of TermResult-----------*/
    @Override
    public List<String> fetchTerm() {
        List<String> termResults = userSearchServiceRepository.findTerms();
       // System.out.println(termResults.size());
        return termResults;
    }

    /*------------fetchNeoData method for getting List of UserSearchResult-----------*/
    @Override
    public List<UserSearchResult> fetchNeoData(UserInput userInputRef) {

        userInput.setConcept(userInputRef.getConcept());
        userInput.setDomain(userInputRef.getDomain());
        userInput.setTerm(userInputRef.getTerm());
        logger.debug(userInput.getConcept());
       // System.out.println("hai" + userInput.getTerm());
        List<Map<String, Object>> intentResultIndicatorOfRelation = userSearchServiceRepository.getAllIndicatorRelation(userInput.getTerm());
        //System.out.println(intentResultIndicatorOfRelation.size());
        List<Map<String, Object>> intentResultRelatesRelation = userSearchServiceRepository.getAllRelatesRelation(userInput.getConcept());
        //.out.println("" + intentResultRelatesRelation.size());

        /*exception handling*/
        try {
            if (intentResultRelatesRelation == null) {
                throw new NeoDataNotFetchedException("Empty data in database");
            }
        } catch (NeoDataNotFetchedException e) {
            e.printStackTrace();
        }

			/*exception handling*/

			for(Map<String,Object> result1 : intentResultIndicatorOfRelation) {
                for (Map<String,Object> result2 : intentResultRelatesRelation) {
                    if (result1.get("intentName").equals(result2.get("intent"))) {

                        for (Map<String, Object> map1 : intentResultRelatesRelation) {
                            UserSearchResult userSearchResult = new UserSearchResult();
                            userSearchResult.setUrl("" + map1.get("url"));
                            userSearchResult.setDescription("" + map1.get("description"));
                            userSearchResult.setConfidenceScore((float) Double.parseDouble("" + map1.get("confidenceScore")));
                            userSearchResults.add(userSearchResult);

                            System.out.println(userSearchResults);


                        }

                    }
                }

            }

        return userSearchResults;
        }

    }





