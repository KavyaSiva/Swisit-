package com.stackroute.swisit.intentparser.repository;
/*-----------Importing Libraries------------*/
import com.stackroute.swisit.intentparser.domain.Intent;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*------------Repository for Intent Data of Neo4j DB-----------*/
@Repository
public interface IntentRepository extends GraphRepository<Intent>{
    /*------Query to return all Intents from Database------*/
    @Query("MATCH (n:`Intent`) RETURN n")
    List<Intent> findIntents();
   
    /*------Query to Create Document Nodes------*/
    @Query("CREATE (u:Document {url:{url}})")
    void createDocumentNode(@Param("url") String url);
}

