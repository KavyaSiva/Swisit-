package com.stackroute.swisit.documentparser.service;

import com.stackroute.swisit.documentparser.domain.Term;

import java.util.List;

/**
 * Created by user on 30/6/17.
 */
public interface Neo4jParserService {
    public List<Term> getTerms();
}
