package com.stackroute.swisit.intentparser.service;
/*-------Importing Libraries-------*/
import com.stackroute.swisit.intentparser.domain.Intent;
import com.stackroute.swisit.intentparser.domain.CrawlerResult;
import com.stackroute.swisit.intentparser.domain.IntentParserResult;

import java.util.ArrayList;
import java.util.List;
/*-------Intent Parser Algorithms Interface Class--------*/
public interface IntentParseAlgo {

    public List<IntentParserResult> calculateConfidence(Iterable<CrawlerResult> intentInput);
    public ArrayList<IntentParserResult> calculateConfidenceScore(CrawlerResult intentParserInput/*, List<Intent> intentList*/);
    public void get(CrawlerResult cr);
}
