package com.stackroute.swisit.documentparser.service;

/*-------- Importing Libraries --------*/
import org.jsoup.nodes.Document;

import java.util.HashMap;

/**
 * Created by user on 30/6/17.
 */
public interface KeywordScannerService {

    public HashMap<String, String> scanDocument(Document document);
}
