package com.stackroute.swisit.documentparser.service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 30/6/17.
 */
public interface ConceptNetService {
    public HashMap<String,HashMap<String,Integer>> createDocumentModel(HashMap<String,List<String>> input);
}
