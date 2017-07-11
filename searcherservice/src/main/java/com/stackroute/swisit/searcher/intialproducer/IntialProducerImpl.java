package com.stackroute.swisit.searcher.intialproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.swisit.searcher.domain.SearcherJob;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
@Service
public class IntialProducerImpl implements IntialProducer{

	@Value("${brokerid}")
	String brokerid;
	/* kafka producer for searcherJob */
	@Override
	public void publishMessage(String topic, SearcherJob message) throws JsonProcessingException {
		// TODO Auto-generated method stub
		Properties configProperties = new Properties();
		/* configure the properties for kafka */
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.165:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put("value.serializer","com.stackroute.swisit.searcher.kafkaserialization.QuerySerializer");
        /* produce data to kafka */
        Producer producer = new KafkaProducer(configProperties);
        ProducerRecord<String, SearcherJob> rec = new ProducerRecord<String, SearcherJob>(topic,message);
        producer.send(rec);
        producer.close();
	
	}

}
