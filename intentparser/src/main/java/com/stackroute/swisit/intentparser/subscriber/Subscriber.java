package com.stackroute.swisit.intentparser.subscriber;
/*-------Importing Libraries-------*/
import com.stackroute.swisit.intentparser.domain.CrawlerResult;

import java.util.List;
/*--------Interface class for Kafka subscriber-------*/
public interface Subscriber {
    public List<CrawlerResult> receivingMessage(String string);
}
