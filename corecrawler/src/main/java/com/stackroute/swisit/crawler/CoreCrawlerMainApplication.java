package com.stackroute.swisit.crawler;

import java.io.File;
/*--------------- Importing Libraries --------------*/
import java.io.IOException;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stackroute.swisit.crawler.domain.SearcherResult;
import com.stackroute.swisit.crawler.service.MasterScannerServiceImpl;
import com.stackroute.swisit.crawler.subscriber.KafkaSubscriberImpl;
import com.stackroute.swisit.crawler.threadconsumer.KafkaConsumer;
//import com.stackroute.swisit.intentparser.threadconsumer.KakfaConsumer;

/*-------------Spring Boot Application Main Class--------------*/
@SpringBootApplication
//@EnableEurekaClient
public class CoreCrawlerMainApplication extends WebMvcConfigurerAdapter{
	
	@Value("${topic-fromconsumer}")
	static String consumerTopic;

	/*-----------------Main method where execution starts ------------------*/
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		ConfigurableApplicationContext applicationContext =SpringApplication.run(CoreCrawlerMainApplication.class, args);
		 KafkaConsumer kakfaConsumer = applicationContext.getBean(KafkaConsumer.class);
	        kakfaConsumer.consumeMessage("testcontrolfinal1");
		//KafkaSubscriberImpl kafkaSubscriberImpl = applicationContext.getBean(KafkaSubscriberImpl.class);
		//List<SearcherResult> list=kafkaSubscriberImpl.receivingMessage("testcontrolfinal");
		//SearcherResult searcherResult[]= new SearcherResult[list.size()];
		//list.toArray(searcherResult);
		
		/*ObjectMapper mapper = new ObjectMapper();
		File file = new File("./src/main/resources/common/sample.json");
	    SearcherResult[] searcherResult=mapper.readValue(file, SearcherResult[].class);*/
		
		//MasterScannerServiceImpl masterScannerServiceImpl = applicationContext.getBean(MasterScannerServiceImpl.class);
		//masterScannerServiceImpl.scanDocument(searcherResult);
	}

	/*-------------- Methods to implement internationalization --------------*/

	/*----------------------Resolving Locale-------------------------*/
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}
	
	/*------------------Loading Message Bundles--------------------*/
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/messages");
		return messageSource;
	}

	/*----Interceptor that allows for changing the current locale on every request---*/
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/*-------------Changing Language with User Preference------------*/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
