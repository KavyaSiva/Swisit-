package com.stackroute.swisit.searcher.loadbalancing;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/* Producing and consuming messages for loadbalancing */
@Service
public class LoadBalancingProducer implements LoadBalancing{
	
	@Value("${brokerid}")
	String brokerid;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/* Kafka producer for load balancing*/
	@Override
    public void LoadProducer() {
    
         Properties configProperties = new Properties();
         /* configuring the properties for kafka */
         configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerid);
         configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
         configProperties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
            Producer producer = new KafkaProducer(configProperties);   
            for (int i = 0; i < 100; i++) {
                String msg = "Message " + i;
                producer.send(new ProducerRecord<String, String>("producer", msg));
                logger.debug("Sent:" + msg);
            }
    }

	/* kafka consumer for load balancing */
    @Override
    public void LoadConsumer() {
        // TODO Auto-generated method stub
        Properties properties = new Properties();
        /* configuring the properties for kafka */
        properties.put("group.id", "group-1");
        
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.165:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("kafkaproducer"));
        
        
        while (true) {
          ConsumerRecords<String, String> records = kafkaConsumer.poll(100000);
          for (ConsumerRecord<String, String> record : records) {
              //searcherResultKafka.add(record.value());
              logger.debug(record.value());
          
          }
          
        }
    }
}
