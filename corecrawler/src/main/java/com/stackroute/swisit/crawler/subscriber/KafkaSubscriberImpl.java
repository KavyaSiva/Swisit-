package com.stackroute.swisit.crawler.subscriber;

import java.io.IOException;
/*-------Importing Libraries------*/
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stackroute.swisit.crawler.domain.SearcherResult;
import com.stackroute.swisit.crawler.service.MasterScannerService;

@Service
public class KafkaSubscriberImpl implements Subscriber{
	@Autowired
	MasterScannerService masterScannerService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${broker-id}")
	String brokerid;
	public List<SearcherResult> receivingMessage(String string) throws JsonParseException, JsonMappingException, IOException {

		Properties properties = new Properties();
		/* configure properties for kafka */
		properties.put("group.id", "group-1");
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , "172.23.239.165:9092");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "com.stackroute.swisit.crawler.domain.SearcherResult");
		List<SearcherResult> searcherResultKafka=new ArrayList<SearcherResult>();
		
		/* consume message from the kafka topic */
		KafkaConsumer<String, SearcherResult> kafkaConsumer = new KafkaConsumer(properties);
		kafkaConsumer.subscribe(Arrays.asList(string));

		while (true) {
			ConsumerRecords<String, SearcherResult> records = kafkaConsumer.poll(10000);
			for (ConsumerRecord<String, SearcherResult> record : records) {
				searcherResultKafka.add(record.value());
				SearcherResult sr = new SearcherResult();
				sr=record.value();
				if(sr!=null)
				masterScannerService.scanDocument(sr);
			}
			return searcherResultKafka;
		}
	}
}
