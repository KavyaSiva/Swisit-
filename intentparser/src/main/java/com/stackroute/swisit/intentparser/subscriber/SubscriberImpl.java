package com.stackroute.swisit.intentparser.subscriber;
/*-------Importing Libraries------*/
import com.stackroute.swisit.intentparser.domain.CrawlerResult;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/*-------Implementation Class for Kafka Subscriber------*/
@Service
public class SubscriberImpl implements Subscriber{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public List<CrawlerResult> receivingMessage(String string) {
        Properties props = new Properties();
        props.put("group.id", "group-1");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.165:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","com.stackroute.swisit.intentparser.serialization.CrawlerDeserializer");
        List<CrawlerResult> final_kafka=new ArrayList<CrawlerResult>();
        KafkaConsumer<String, CrawlerResult> kafkaConsumer = new KafkaConsumer<String, CrawlerResult>(props);
        kafkaConsumer.subscribe(Arrays.asList(string));
        while (true) {
            ConsumerRecords<String, CrawlerResult> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, CrawlerResult> record : records) {
                logger.debug("inside consumer i am getting"+record.value());
                final_kafka.add(record.value());
            }
            
            return final_kafka;
        }
        }
}
