package com.stackroute.swisit.documentparser.repository;

import com.stackroute.swisit.documentparser.domain.Term;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 30/6/17.
 */
@Repository
public interface Neo4jParserRepository extends GraphRepository {

    @Query("Match (t:Term) Return t")
    public List<Term> getTerms();
}
