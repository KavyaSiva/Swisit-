package com.stackroute.swisit.intentparser.threadconsumer;
//package com.stackroute.swisit..threadconsumer;

import java.io.IOException;
import java.util.*;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.stackroute.swisit.crawler.domain.SearcherResult;
//import com.stackroute.swisit.crawler.domain.SearcherResult;
//import com.stackroute.swisit.crawler.service.MasterScannerService;
import com.stackroute.swisit.intentparser.domain.CrawlerResult;
import com.stackroute.swisit.intentparser.service.IntentParseAlgo;

public class KafkaConsumerThread extends Thread {
	private String topicName;
	private String groupId;
	private KafkaConsumer<String, CrawlerResult> kafkaConsumer;
	private IntentParseAlgo intentParseAlgo;
	private Environment environment;
	//@Autowired
	//MasterScannerService masterScannerService;
	//----------------------------------logger implementation----------------------------------
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public KafkaConsumerThread(String topicName, IntentParseAlgo intentParseAlgo ){
		this.topicName = topicName;
		//this.groupId = groupId;
		this.intentParseAlgo = intentParseAlgo;
		//this.circleService = circleService;
		//this.environment = environment;
	}

	public void run() {
		Properties configProperties = new Properties();
		configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.165:9092");
		configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.stackroute.swisit.intentparser.serialization.CrawlerDeserializer");
		configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
		//configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");
		//List<SearcherResult> searcherResultKafka=new ArrayList<SearcherResult>();
		Set<CrawlerResult> final_kafka=new HashSet<>();
		//Figure out where to start processing messages from
		kafkaConsumer = new KafkaConsumer<String, CrawlerResult>(configProperties);
		kafkaConsumer.subscribe(Arrays.asList(topicName));

		//Start processing messages
		while (true) {
			ConsumerRecords<String, CrawlerResult> records = kafkaConsumer.poll(10000);
			for (ConsumerRecord<String, CrawlerResult> record : records) {
				//JsonNode node = objectMapper.readTree(record.value());
				//logger.debug(record.value());
				int count=0;
				CrawlerResult crawlerResult = new CrawlerResult();
				crawlerResult = record.value();
				System.out.println("hi i am getting "+crawlerResult.getConcept());
				try {
					System.out.println("count is" + count);
					final_kafka.add(record.value());
					intentParseAlgo.calculateConfidenceScore(record.value());
					//implenment here
					//intentParseAlgo.calculateConfidenceScore(intentParserInput, intentList)
					//(searcherResult);
					count++;
				} catch (Exception e) {
					e.printStackTrace();
				}

				//final_kafka.clear();
				System.out.println(record.value());
				//circleService.getActivityType(node); 
			}
			//intentParseAlgo.calculateConfidence(final_kafka);
			//final_kafka.clear();
			//final_kafka=null;
		}

	}
	public KafkaConsumer<String,CrawlerResult> getKafkaConsumer()
	{
		return this.kafkaConsumer;
	}
}
