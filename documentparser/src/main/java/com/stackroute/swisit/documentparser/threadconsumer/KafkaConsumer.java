package com.stackroute.swisit.documentparser.threadconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.stackroute.swisit.crawler.service.MasterScannerService;
import com.stackroute.swisit.documentparser.service.MasterParserService;
@Service
public class KafkaConsumer {

	@Autowired
	MasterParserService masterScannerService;

	public void consumeMessage(String topic){
		KafkaConsumerThread consumerRunnable = new KafkaConsumerThread(topic,masterScannerService);
		consumerRunnable.start();
		consumerRunnable.getKafkaConsumer().wakeup();
//		try {
//			consumerRunnable.join(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
