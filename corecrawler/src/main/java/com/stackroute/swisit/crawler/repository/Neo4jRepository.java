package com.stackroute.swisit.crawler.repository;

/*--------------- Importing Libraries --------------*/
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.swisit.crawler.domain.Term;

/*------------Repository for Terms of Neo4j DB-----------*/
@Repository
public interface Neo4jRepository extends GraphRepository<Term>{
	
	/*--------- Method declaration to fetch terms from neo4j using query--------*/
	@Query("MATCH (n:`Term`) RETURN n")
	List<Term> findTerms();
	
	/*--------- Method declaration to fetch terms from neo4j using query--------*/
	@Query("Match (d:`Term`) return d")
	List<Term> fetchTerms();
}