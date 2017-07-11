package com.stackroute.swisit.intentparser.repository;
/*-----------Importing Libraries------------*/
import com.stackroute.swisit.intentparser.domain.Term;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.annotation.Query;
import java.util.List;
/*------------Repository for Terms Data of Neo4j DB-----------*/
@Repository
public interface TermRepository extends GraphRepository<Term> {

    /*-----Query to get all Terms from database----*/
    @Query("MATCH (n:`Term`) RETURN n")
    List<Term> findTerms();
}
