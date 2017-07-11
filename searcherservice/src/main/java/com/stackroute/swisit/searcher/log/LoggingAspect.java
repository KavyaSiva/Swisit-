package com.stackroute.swisit.searcher.log;

	
	import org.aspectj.lang.annotation.Aspect;
	import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

	@Aspect
	@Component
	/* Implementing Aspect Oriented Programming for Searcher Service */
	public class LoggingAspect {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());
	    
	    @Before("execution(public String getTitle())")
	    public void onBean()
	    {
	        logger.debug("inside bean advice");
	    }

}
