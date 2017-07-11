package com.stackroute.swisit.searcher.messageservice;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.swisit.searcher.domain.SearcherResult;
@Service
public class MessageServiceImpl implements MessageService {
	
	@Value("${brokerid}")
	String brokerid;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void publishMessage(String topic, SearcherResult message) throws JsonProcessingException {
		// TODO Auto-generated method stub
		
		Properties configProperties = new Properties();
		/* Configure properties for Kafka */
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.165:9092");
		configProperties.put("key.serializer","org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put("value.serializer","com.stackroute.swisit.searcher.kafkaserialization.SwisitSerializer");
		Producer producer = new KafkaProducer(configProperties);
		ProducerRecord<String, SearcherResult> rec = new ProducerRecord<String, SearcherResult>(topic,message);
		producer.send(rec);
		producer.close();
	}
	

	@Override
	public void listenMessage(String topic) {
		Properties props = new Properties();
		/* Configure properties for Kafka */
		props.put("group.id", "group-1");
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.165:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");			
		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
		kafkaConsumer.subscribe(Arrays.asList(topic));
	    while (true) {
	    ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
	    for (ConsumerRecord<String, String> record : records) {
	    	logger.debug("inside consumer i am getting"+record.value());
	    }
	 }

}

}