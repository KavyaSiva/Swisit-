package com.stackroute.swisit.documentparser.subscriber;

import java.text.ParseException;
/*------------ Importing Libraries-----------*/
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.swisit.documentparser.domain.CrawlerResult;
import com.stackroute.swisit.documentparser.service.MasterParserService;

/*-- Kafka subscriber that implements Subscriber interface to receive the message from kafka --*/
@Service
public class KafkaSubscriberImpl implements Subscriber {
	@Autowired
	MasterParserService masterParserService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/* Overriding receiveMessage of the interface to receive message from kafka as list
	 * arguments- topic name
	 * returns- list of Crawler result
	 *  */
	@Override
	public List<CrawlerResult> receiveMessage(String topic) throws JsonProcessingException, ParseException {
		Properties properties = new Properties();
		properties.put("group.id", "group-1");
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , "172.23.239.165:9092");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "com.stackroute.swisit.documentparser.serialization.CrawlerDeserializer");

		List<CrawlerResult> crawlerResultKafka=new ArrayList<CrawlerResult>();
		KafkaConsumer<String, CrawlerResult> kafkaConsumer = new KafkaConsumer(properties);
		kafkaConsumer.subscribe(Arrays.asList(topic));

		while (true) {
			ConsumerRecords<String, CrawlerResult> records = kafkaConsumer.poll(10000);
			//logger.info("records  "+records);
			for (ConsumerRecord<String, CrawlerResult> record : records) {
				//logger.info("record  "+record);
				CrawlerResult cr= new CrawlerResult();
				cr=record.value();
				if(cr!=null)
				masterParserService.parseDocument(cr);
				crawlerResultKafka.add(record.value());

			}

			return crawlerResultKafka;
		}
	}

}


