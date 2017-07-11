package com.stackroute.swisit.crawler.threadconsumer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.springframework.core.env.Environment;

import com.stackroute.swisit.crawler.domain.CrawlerResult;
import com.stackroute.swisit.crawler.domain.SearcherResult;
import com.stackroute.swisit.crawler.service.MasterScannerService;

//import com.stackroute.swisit.documentparser.domain.CrawlerResult;
//import com.stackroute.swisit.documentparser.service.MasterParserService;

public class KafkaConsumerThread extends Thread {
	private String topicName;
	//private String groupId;
	private KafkaConsumer<String, SearcherResult> kafkaConsumer;
	private MasterScannerService masterScannerService;
	//private Environment environment;

	public KafkaConsumerThread(String topicName, MasterScannerService masterScannerService ){
		this.topicName = topicName;
		this.masterScannerService = masterScannerService;
	}

	public void run() {
		Properties configProperties = new Properties();
		configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.165:9092");
		configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.stackroute.swisit.crawler.domain.SearcherResult");
		configProperties.put("group.id", "group-1");

		/*Figure out where to start processing messages from*/

		kafkaConsumer = new KafkaConsumer<String, SearcherResult>(configProperties);
		kafkaConsumer.subscribe(Arrays.asList(topicName));

		/*Start processing messages*/

		while (true) {
			ConsumerRecords<String, SearcherResult> records = kafkaConsumer.poll(10000);
			for (ConsumerRecord<String, SearcherResult> record : records) {
				SearcherResult searcherResult = new SearcherResult();
				Set<SearcherResult> s= new HashSet<SearcherResult>();
				
				
				searcherResult = record.value();
				System.out.println(searcherResult.getLink()); 
				try {
					masterScannerService.scanDocument(searcherResult);
				} catch (Exception e) {
					e.printStackTrace();
				}

				//System.out.println(record.value()); 
			}
		}

	}
	public KafkaConsumer<String,SearcherResult> getKafkaConsumer() {
		return this.kafkaConsumer;
	}
}
