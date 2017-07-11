package com.stackroute.swisit.crawler.exception;

/*------ User defined exception class to handle exception during DOM creation ------*/
@SuppressWarnings("serial")
public class DOMNotCreatedException extends Exception {

	public DOMNotCreatedException(String message) {
		super(message);
	}
}
