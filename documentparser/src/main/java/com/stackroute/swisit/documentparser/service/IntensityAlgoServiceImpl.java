package com.stackroute.swisit.documentparser.service;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import com.stackroute.swisit.documentparser.exception.TitleIntensityCalculationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.swisit.documentparser.domain.ContentSchema;


/**
 * Created by user on 30/6/17.
 */
@Service
public class IntensityAlgoServiceImpl implements IntensityAlgoService {

    float count;

    @Autowired
    ObjectMapperService objectMapperService;

    public ArrayList<ContentSchema> calculateIntensity(HashMap<String, HashMap<String, Integer>> parsedDocumentMap) {
            ArrayList<ContentSchema> contentSchemas = new ArrayList<>();
            List<LinkedHashMap<String, String>> list = objectMapperService.objectMapping("./src/main/resources/common/termintensity.json");
            HashMap<String, String> map = new HashMap<>();
            String wordKey = "";
        try {
            if (parsedDocumentMap == null) {
                throw new TitleIntensityCalculationException("Intensity not calculated");
            }
            for (LinkedHashMap<String, String> linked : list) {
                // Entry<String,String> keyset = linked.entrySet<String,String>();
                Iterator<Map.Entry<String, String>> entries = linked.entrySet().iterator();
                Map.Entry<String, String> entry = entries.next();
                map.put(entry.getKey(), entry.getValue());
            }
            for (Entry<String, HashMap<String, Integer>> parsedDocumentMapRef : parsedDocumentMap.entrySet()) {
                wordKey = parsedDocumentMapRef.getKey();
                System.out.println("My term is: " + wordKey);
                for (Entry<String, Integer> wordKeyValueRef : parsedDocumentMapRef.getValue().entrySet()) {
                    String tagKey = wordKeyValueRef.getKey();
                    Integer tagFrequency = wordKeyValueRef.getValue();
                    float intensity = Float.parseFloat(map.get(tagKey));
                    System.out.println("tagKey " + tagKey + "frequency " + tagFrequency);
                    count += tagFrequency * intensity;

                }
                ContentSchema contentSchema = new ContentSchema(wordKey, count);
                contentSchemas.add(contentSchema);
                //System.out.println("word is " + wordKey + " count is " + count);
                count = 0;
            }
        /*for(ContentSchema cs:contentSchemas){
            System.out.println("term : "+cs.getWord()+"         intensity : "+cs.getIntensity());
        }*/
        }
        catch (TitleIntensityCalculationException e){
            e.printStackTrace();

        }
        return contentSchemas;
    }
}

