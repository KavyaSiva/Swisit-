package com.stackroute.swisit.documentparser.service;

import com.stackroute.swisit.documentparser.domain.Term;
import com.stackroute.swisit.documentparser.repository.Neo4jParserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 30/6/17.
 */
@Service
public class Neo4JParserServiceImpl implements Neo4jParserService {

    @Autowired
    Neo4jParserRepository neo4jParserRepository;

    public List<Term> getTerms(){
        List<Term> termsList = neo4jParserRepository.getTerms();
        return termsList;
    }
}
