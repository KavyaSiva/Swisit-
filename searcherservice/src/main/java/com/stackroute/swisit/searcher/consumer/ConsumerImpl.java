package com.stackroute.swisit.searcher.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class ConsumerImpl implements Consumer{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${brokerid}")
	String brokerid;

	@Override
	public void listenMessage(String topic) {
		Properties props = new Properties();
	    props.put("group.id", "group-1");
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerid);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		/* consume data from the topic */
	    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
	    kafkaConsumer.subscribe(Arrays.asList(topic));
	    while (true) {
	      ConsumerRecords<String, String> records = kafkaConsumer.poll(10000);
	      for (ConsumerRecord<String, String> record : records) {
	    	  logger.debug("inside consumer i am getting"+record.value());
	    	  }
	    }

	
	}

}
