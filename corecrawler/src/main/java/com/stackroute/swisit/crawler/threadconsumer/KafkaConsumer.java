package com.stackroute.swisit.crawler.threadconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.swisit.crawler.service.MasterScannerService;
//import com.stackroute.swisit.crawler.service.MasterScannerService;
//import com.stackroute.swisit.documentparser.service.MasterParserService;
@Service
public class KafkaConsumer {

	@Autowired
	MasterScannerService masterScannerService;

	public void consumeMessage(String topic){
		KafkaConsumerThread consumerRunnable = new KafkaConsumerThread(topic,masterScannerService);
		consumerRunnable.start();
		consumerRunnable.getKafkaConsumer().wakeup();
		//consumerRunnable.destroy();
//		try {
//			//consumerRunnable.join(100);
//		} //catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
