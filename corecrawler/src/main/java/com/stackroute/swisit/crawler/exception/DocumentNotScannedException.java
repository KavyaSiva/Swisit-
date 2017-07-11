package com.stackroute.swisit.crawler.exception;

/*------ User defined exception class to handle exception during DOM scanning ------*/
@SuppressWarnings("serial")
public class DocumentNotScannedException extends Exception {
	
	public DocumentNotScannedException(String messageException) {
		super(messageException);
	}
}
