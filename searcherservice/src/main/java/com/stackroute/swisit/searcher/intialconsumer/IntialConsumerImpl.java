package com.stackroute.swisit.searcher.intialconsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stackroute.swisit.searcher.domain.SearcherJob;
@Service
public class IntialConsumerImpl implements IntialConsumer{
	@Value("${brokerid}")
	String brokerid;
	/* kafka consumer for searcherJob */
	@Override
	public SearcherJob listenMessage(String topic) {
		Properties props = new Properties();
		
			/* configuring the properties for kafka */
			props.put("group.id", "group-1");
		    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.165:9092");
			props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		    props.put("value.deserializer", "com.stackroute.swisit.searcher.kafkaserialization.QueryDeserializer");
		    List<SearcherJob> l=new ArrayList<SearcherJob>();
		    /* initial consumer is used to consume data from kafka */
		    KafkaConsumer<String, SearcherJob> kafkaConsumer = new KafkaConsumer<>(props);
		    kafkaConsumer.subscribe(Arrays.asList(topic));
		    SearcherJob q=new SearcherJob();
		    while (true) {
		      ConsumerRecords<String, SearcherJob> records = kafkaConsumer.poll(10000);
		      for (ConsumerRecord<String, SearcherJob> record : records) {
		    	  q.setDomain(record.value().getDomain());
		    	  q.setResults(record.value().getResults());//=record.value();
		    	  q.setConcept(record.value().getConcept());
		    	  q.setSitesearch(record.value().getSitesearch());
		      }
		return q;
		}

	}
	

}
