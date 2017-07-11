/**...*/
package com.stackroute.swisit.usersearchservice.repository;
/*-----------Importing Libraries------------*/
import com.stackroute.swisit.usersearchservice.domain.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/*------------Repository for Relationship Data of Neo4j DB-----------*/
@Repository
public interface UserSearchServiceRepository extends GraphRepository {


    /*------Query to return all Intents from Database------*/
    @Query("MATCH (n:`Intent`) RETURN n")
    List<Intent> findIntents();

    /*------Query to return all Concepts from Database------*/
    @Query("MATCH (d:Domain)<-[r:ConceptOf]-(c:Concept) RETURN c.name AS conceptName ")
    // List<ConceptResult> findConcepts();
    List<String> findConcepts();


    /*-----Query to get all Terms from database----*/
    @Query("MATCH (n:`Term`) RETURN n.name AS termName")
    List<String> findTerms();

    /*----------Query to get indicatorOf Relation between Terms and Intents----------*/
    @Query("Match (t:Term  {name: {termName}})-[r:indicatorOf]->(i:Intent) return t.name AS termName,i.name AS intentName,type(r) AS relName,r.weight AS weight ORDER BY r.weight DESC")
    List<Map<String,Object>> getAllIndicatorRelation(@Param("termName") String termName);


    /*----------Query to get intentOf Relation between Concept and Document----------*/
    @Query("Match (c:Concept  {name: {conceptName}})<-[r:Relates]-(d:Document) return c.name AS conceptName,d.url AS url,type(r) AS relName,r.intent AS intent,r.confidenceScore AS confidenceScore ORDER BY r.confidenceScore DESC")
    List<Map<String,Object>> getAllRelatesRelation(@Param("conceptName") String conceptName);

}
