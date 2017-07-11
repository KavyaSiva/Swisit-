package com.stackroute.swisit.crawler.service;

import java.io.IOException;
/*-------Importing Libraries------*/
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stackroute.swisit.crawler.exception.DOMNotCreatedException;

/*-- DOMCreatorServiceImpl implements DOMCreatorService interface to construct DOM --*/
@Service
public class DOMCreatorServiceImpl implements DOMCreatorService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	HashSet<String> links=new HashSet<>();

	/* Method implementation to construct DOMs
	 * argument- link
	 * returns- Document
	 * */
	@Override
	public Document constructDOM(String link) throws DOMNotCreatedException, IOException {
		logger.info(link);
		Document doc = null;
		doc=Jsoup.connect(link).get();
		return doc;
	}

}
