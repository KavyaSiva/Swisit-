package com.stackroute.swisit.documentparser.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.swisit.documentparser.exception.FilePathNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by user on 2/7/17.
 */
@Service
public class ObjectMapperServiceImpl implements ObjectMapperService {

    @Override
    public List<LinkedHashMap<String, String>> objectMapping(String filePath) {

        ObjectMapper objectMapper=new ObjectMapper();
        File file= new File(filePath);
        System.out.println(file.getName());
        List<LinkedHashMap<String,String>> list = null;
        try {
            if (filePath == null) {
                throw new FilePathNotFoundException("Path not found");
            }

            list = objectMapper.readValue(file, ArrayList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(LinkedHashMap<String,String> linked : list){
           // System.out.println(linked.toString());
        }

        return list;
    }
}
