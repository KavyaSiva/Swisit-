package com.stackroute.swisit.documentparser.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.swisit.documentparser.domain.DocumentParserResult;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by user on 5/7/17.
 */
public class CrawlerSerializer implements Serializer<DocumentParserResult> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, DocumentParserResult data) {
       byte[] b=null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            b = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
       // return new byte[0];
    }

    @Override
    public void close() {

    }
}
