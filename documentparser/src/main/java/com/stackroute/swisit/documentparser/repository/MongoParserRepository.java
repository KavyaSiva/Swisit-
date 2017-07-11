package com.stackroute.swisit.documentparser.repository;

import com.stackroute.swisit.documentparser.domain.DocumentModel;
import com.stackroute.swisit.documentparser.domain.DocumentParserResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 30/6/17.
 */
@Repository
public interface MongoParserRepository extends MongoRepository<DocumentModel,String> {
}
