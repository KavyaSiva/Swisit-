package com.stackroute.swisit.intentparser.repository;
/*-----------Importing Libraries------------*/
import com.stackroute.swisit.intentparser.domain.IndicatorOf;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*------------Repository for Relationship Data of Neo4j DB-----------*/
@Repository
public interface RelationshipRepository extends GraphRepository<IndicatorOf> {

    /*----------Query to get indicatorOf Relation between Terms and Intents----------*/
    @Query("MATCH ()-[r:indicatorOf]->() RETURN r")
    Iterable<Map<String,Object>> getAllIndicator();

    /*----------Query to get counterIndicatorOf Relation between Terms and Intents----------*/
    @Query("MATCH ()-[r:counterIndicatorOf]->() RETURN r")
    Iterable<Map<String,Object>> getAllCounterIndicator();

    /*----------Query to get IntentOf Relation between Intents and Domain----------*/
    @Query("MATCH ()-[r:IntentOf]->() RETURN r")
    Iterable<Map<String,Object>> getAllIntentOF();

    /*----------Query to get all Relation between Terms and Intents----------*/
    @Query("Match p=(t:Term)-[r]->(i:Intent) RETURN r")
    Iterable<Map<String,Object>> getBothRelationships();

    /*----------Query to get all Relations between Terms and Intents----------*/
    @Query("Match p=(t:Term)-[]->(i:Intent)-[]->(d:Intent) RETURN p")
    Iterable<Map<String,Object>> getAllRelationships();

    /*----------Query to get all Relations between Terms and Intents as IntentParserResult----------*/
    @Query("Match (t:Term)-[r]->(i:Intent) return t.name AS termName,i.name AS intentName,type(r) AS relName,r.weight AS weight")
    List<Map<String,String>> fetchAllRelationships();

    /*----------Query to get Terms Relation between Terms and an Intent----------*/
    @Query("Match (t:Term)-[r]->(i:Intent {name: {intentName}}) return t.name AS termName,i.name AS intentName,type(r) AS relName,r.weight AS weight")
    List<Map<String,String>> getAllTermsRelationOfIntent(@Param("intentName") String intentName);

//    /*----------Query to create Resulted Relations between URLs and Concepts----------*/
//    // @Query("MATCH (w:Document {url:{url}}) MATCH (c:Concept {name:{concept}}) call apoc.create.relationship(w,{intentName},{confidenceScore:{confScore}},c) YIELD rel AS r Return w,r,c")
//    @Query("Match (d:Document),(c:Concept) where d.url={url} and c.name={concept} create (d)-[r:Relates{intent:{intentName},confidenceScore:{confScore}}]->(c) return d.url AS url, c.name AS concept, r.intent AS intentName, r.confidenceScore AS confidenceScore")
//    //@Query("MATCH (d:Document {url:{url}}),(c:Concept{name:{concept}}) CREATE (d)-[r:relates{intent:{intentName},confidenceScore:{confScore}}]->(c) RETURN r,c,d")
//    Map<String,String> createDocToConceptRels(@Param("url") String url,@Param("intentName") String intentName,@Param("confScore") float confidenceScore,@Param("concept") String conceptName);
}

