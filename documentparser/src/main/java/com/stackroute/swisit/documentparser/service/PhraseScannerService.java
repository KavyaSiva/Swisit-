package com.stackroute.swisit.documentparser.service;

import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 30/6/17.
 */
public interface PhraseScannerService {
    public HashMap<String,List<String>> scanDocument(Document document);
    public List<String> ngrams(int n, String str);
    public String concat(String[] words, int start, int end);
}
