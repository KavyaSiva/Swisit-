package com.stackroute.swisit.searcher.publisher;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.swisit.searcher.domain.SearcherResult;

@Service
public class PublisherImpl implements Publisher {

	@Value("${brokerid}")
	String brokerid;
	
	@Override
	public void publishMessage(String topic, SearcherResult message) throws JsonProcessingException {
		// TODO Auto-generated method stub
		Properties configProperties = new Properties();
		/* configure properties for Kafka */
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.165:9092");
		configProperties.put("key.serializer","org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put("value.serializer","com.stackroute.swisit.searcher.kafkaserialization.SwisitSerializer");
		Producer producer = new KafkaProducer(configProperties);
		ProducerRecord<String, SearcherResult> rec = new ProducerRecord<String, SearcherResult>(topic,message);
		System.out.println("topic is "+topic+" "+"link is "+message.getTitle()+" "+message.getUrl());
		producer.send(rec);
		producer.close();
	
	}

}
