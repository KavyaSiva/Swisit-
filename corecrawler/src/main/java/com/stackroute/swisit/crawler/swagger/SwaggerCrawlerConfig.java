package com.stackroute.swisit.crawler.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerCrawlerConfig {
	
	    @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .	apis(RequestHandlerSelectors.basePackage("com.stackroute.swisit.crawler.controller"))
	                .paths(regex("/v1/api.*"))
	                .build()
	                .apiInfo(metaData());
	    }
	    /* method to insert data to swagger documentation */
	    private ApiInfo metaData() {
	           ApiInfo apiInfo = new ApiInfo(
	                   "SWIS-it CrawlerService API",
	                   "CrawlerService REST API",
	                   "1.0",
	                   "Terms of service",
	                   new Contact("CrawlerService", "https://stackroute.in/", "simanta.sarma@stackroute.in"),
	                   "Apache License Version 2.0",
	                   "https://www.apache.org/licenses/LICENSE-2.0");
	           return apiInfo;
	       }

}
