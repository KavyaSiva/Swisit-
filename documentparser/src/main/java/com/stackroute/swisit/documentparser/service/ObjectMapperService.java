package com.stackroute.swisit.documentparser.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by user on 2/7/17.
 */
public interface ObjectMapperService {
    public List<LinkedHashMap<String, String>> objectMapping(String filePath) ;
}
