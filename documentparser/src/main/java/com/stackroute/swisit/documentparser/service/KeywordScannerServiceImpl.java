package com.stackroute.swisit.documentparser.service;
/*---------- Importing Libraries ---------*/
import com.stackroute.swisit.documentparser.exception.DocumentNotScannedException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by user on 30/6/17.
 */
@Service
public class                                                                                                                                     KeywordScannerServiceImpl implements KeywordScannerService {

    @Autowired
    ObjectMapperService objectMapperService;

    public HashMap<String, String> scanDocument(Document document){

        HashMap<String, String> resultMap = new HashMap<>();
        List<LinkedHashMap<String,String>>  titleList = objectMapperService.objectMapping("./src/main/resources/common/intensity.json");

        List<String> tagList = new ArrayList<>();
        StringTokenizer stringTokenizer = null;

        try {
            if (document == null) {
                throw new DocumentNotScannedException("Document scanning failed");
            }
            for (int i = 0; i < titleList.size(); i++) {
                tagList.add(titleList.get(i).get("title"));
            }
            for (String tag : tagList) {
                //String[] strings ={};
                String tagText = null;
                Elements elements = document.select(tag);
                for (Element element : elements) {
                    tagText = element.text();
                /*stringTokenizer = new StringTokenizer(tagText,"[$&+,:;=?@#|'<>.^*()%!-]");
                strings = null;
                for(int i=0;stringTokenizer.hasMoreElements();i++){
                    strings[i] = stringTokenizer.nextElement().toString();
                }*/
                }
                resultMap.put(tag, tagText);
            }
        }
        catch (DocumentNotScannedException e){
            e.printStackTrace();
        }
        return resultMap;
    }
}
