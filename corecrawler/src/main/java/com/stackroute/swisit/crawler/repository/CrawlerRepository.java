package com.stackroute.swisit.crawler.repository;

/*------------------ Importing Libraries -----------------*/
import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.swisit.crawler.domain.SearcherResult;

/*------------Repository for fetch searcher result from mongo DB-----------*/
public interface CrawlerRepository extends MongoRepository< SearcherResult,String> {

}
