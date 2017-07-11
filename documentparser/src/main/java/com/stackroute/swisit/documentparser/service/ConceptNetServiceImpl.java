package com.stackroute.swisit.documentparser.service;

import com.stackroute.swisit.documentparser.domain.Term;
import com.stackroute.swisit.documentparser.exception.DocumentModelNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by user on 30/6/17.
 */
@Service
public class ConceptNetServiceImpl implements ConceptNetService {

    @Autowired
    Neo4jParserService neo4jParserService;

    public HashMap<String,HashMap<String,Integer>> createDocumentModel(HashMap<String,List<String>> input){

        HashMap<String,HashMap<String,Integer>> resultMap = new HashMap<>();
        List<Term> termsList = neo4jParserService.getTerms();
        System.out.println("Inside create document model");
        try {
            if (input == null) {
                throw new DocumentModelNotCreatedException(" Document model creation failed");
            }
            for (Term term : termsList) {
                HashMap<String, List<String>> map = new HashMap<String, List<String>>();
                Iterator<Map.Entry<String, List<String>>> entries = input.entrySet().iterator();
                HashMap<String, Integer> tagTextMap = new HashMap<>();
                while (entries.hasNext()) {
                    Map.Entry<String, List<String>> entry = entries.next();
                    String tag = entry.getKey();
                    //System.out.println("My tag is ========"+tag);
                    List<String> textValue = entry.getValue();
                    //tagTextMap = null;
                    int count = Collections.frequency(textValue, term.getName());
                    tagTextMap.put(tag, count);
                }
                resultMap.put(term.getName(), tagTextMap);
            }
        }
         catch (DocumentModelNotCreatedException e){
                e.printStackTrace();
            }
        return resultMap;
    }
}
