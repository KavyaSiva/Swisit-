package com.stackroute.swisit.documentparser.service;

import com.stackroute.swisit.documentparser.exception.DocumentNotScannedException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by user on 30/6/17.
 */
@Service
public class PhraseScannerServiceImpl implements PhraseScannerService{

    private ObjectMapperService objectMapperService;

    public HashMap<String,List<String>> scanDocument(Document document){

        HashMap<String,List<String>> resultMap = new HashMap<>();
        List<LinkedHashMap<String,String>> titleList = objectMapperService.objectMapping("./src/main/resources/common/intensity.json");
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
                List<String> strings = null;
                Elements elements = document.select(tag);
                for (Element element : elements) {
                    String tagText = element.text();
                    strings = ngrams(6, tagText);
                }
                resultMap.put(tag, strings);
            }
        }
        catch (DocumentNotScannedException e){
            e.printStackTrace();
        }
        return resultMap;
    }

    public List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split("[$&+,:;=?@#|'<>.^*()%!-]");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i+n));
        return ngrams;
    }

    public String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? " " : "") + words[i]);
        return sb.toString();
    }
}
