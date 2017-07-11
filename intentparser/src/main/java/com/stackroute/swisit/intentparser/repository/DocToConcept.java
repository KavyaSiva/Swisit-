package com.stackroute.swisit.intentparser.repository;

import com.stackroute.swisit.intentparser.domain.IntentParserResult;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DocToConcept extends GraphRepository<IntentParserResult> {
    /*----------Query to create Resulted Relations between URLs and Concepts----------*/
    //@Query("MATCH (w:Document {url:{url}}) MATCH (c:Concept {name:{concept}}) call apoc.create.relationship(w,{intentName},{confidenceScore:{confScore},description:{snippet}},c) YIELD rel AS r Return w,r,c")
    @Query("Match (d:Document),(c:Concept) where d.url={url} and c.name={concept} create (d)-[r:Relates{intent:{intentName},confidenceScore:{confScore},description:{snippet}}]->(c)")
    //@Query("MATCH (d:Document {url:{url}}),(c:Concept{name:{concept}}) CREATE (d)-[r:relates{intent:{intentName},confidenceScore:{confScore}}]->(c) RETURN r,c,d")
    void createDocToConceptRels(@Param("url") String url, @Param("intentName") String intentName, @Param("confScore") float confidenceScore, @Param("concept") String conceptName, @Param("snippet") String snippet);

}
