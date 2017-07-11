package com.stackroute.swisit.searcher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.swisit.searcher.domain.SearcherJob;
import com.stackroute.swisit.searcher.domain.SearcherResult;
/* Saving SearcherJob using MongoRepository */
@Repository
public interface SearcherJobRepository extends MongoRepository<SearcherJob, String>{

}
