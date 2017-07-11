package com.stackroute.swisit.searcher.intialconsumer;

import com.stackroute.swisit.searcher.domain.SearcherJob;
/* kafka consumer used for SearcherJob */
public interface IntialConsumer {
	public SearcherJob listenMessage(String topic);
}
