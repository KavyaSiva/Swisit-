package com.stackroute.swisit.crawler.service;

import java.io.IOException;

/*-------Importing Libraries------*/
import org.jsoup.nodes.Document;

import com.stackroute.swisit.crawler.exception.DOMNotCreatedException;

/*--- DOMCreatorService interface declares a method to construct DOM--*/
public interface DOMCreatorService {
	
	/* Method declaration that is implemented by classes to construct DOMs
	 * argument- link
	 * returns- Document
	 * */
	public Document constructDOM(String link) throws DOMNotCreatedException, IOException;
}
