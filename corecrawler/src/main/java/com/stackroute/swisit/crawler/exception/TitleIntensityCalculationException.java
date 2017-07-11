package com.stackroute.swisit.crawler.exception;

/*------ User defined exception class to handle exception during intensity calculation for terms ------*/
@SuppressWarnings("serial")
public class TitleIntensityCalculationException extends Exception {
	
	public TitleIntensityCalculationException(String msg) {
		super(msg);
	}
}
