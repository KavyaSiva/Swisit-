package com.stackroute.swisit.searcher.loadbalancing;

import org.springframework.stereotype.Service;

@Service
public interface LoadBalancing {
 public void LoadProducer();
 public void LoadConsumer();

 
}
