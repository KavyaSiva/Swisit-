package com.stackroute.swisit.crawler.publisher;

/*----------------- Importing Libraries ----------------*/
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.swisit.crawler.domain.CrawlerResult;

/*
 *  Kafka publisher that implements method of Publisher
 * interface to publish message to intent parser as crawlerResult
 */
@Service
public class KafkaPublisherImpl implements Publisher {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*------ Kafka broker id fetched from properties file------*/
	
	@Value("${broker-id}")
	String brokerid;

	/*-------------------method to publish message via kafka-------------------*/
	public void publishMessage(String topicName,CrawlerResult message) throws JsonProcessingException{
		Properties configProperties = new Properties();
		/* configure properties for kafka */
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.165:9092");
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put("value.serializer","com.stackroute.swisit.crawler.serialization.CrawlerSerializer");
		Producer<String, CrawlerResult> producer = new KafkaProducer<String, CrawlerResult>(configProperties);
		logger.info("getting published");
		ProducerRecord<String, CrawlerResult> producerRecord = new ProducerRecord<String, CrawlerResult>(topicName,message);
		logger.info("Sending........");
		producer.send(producerRecord);
		producer.close();
		logger.info("Closed");
	}

}
