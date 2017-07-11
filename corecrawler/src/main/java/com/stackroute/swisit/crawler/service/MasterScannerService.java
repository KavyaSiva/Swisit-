package com.stackroute.swisit.crawler.service;

/*-------Importing Libraries------*/
import java.io.IOException;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stackroute.swisit.crawler.domain.SearcherResult;

/* MasterScannerService interface declares method to divert to 
 * two major services that is KeywordScannerService and 
 * StructureScannerService to scan for documents according to links
 * from searcher service resultS
 *  */
public interface MasterScannerService {

	/* Method declaration to scan for documents from searcher service result 
	 * to parse for keywords and structure and accordingly provide the intensity
	 * arguments- searcher result array of objects
	 * returns- string 
	 * */
	public String scanDocument(SearcherResult searcherResult) throws JsonParseException, JsonMappingException, IOException;
}
