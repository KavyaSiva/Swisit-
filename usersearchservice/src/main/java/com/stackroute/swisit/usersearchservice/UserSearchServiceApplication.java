package com.stackroute.swisit.usersearchservice;
/*-----------Importing Libraries-----------*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


/*-------------Spring Boot Application Main Class--------------*/
//@EnableDiscoveryClient
//@EnableEurekaClient
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.stackroute.swisit.usersearchservice.*")
@EnableNeo4jRepositories(basePackages = "com.stackroute.swisit.usersearchservice.repository")
public class UserSearchServiceApplication extends Neo4jRepositoriesAutoConfiguration {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(UserSearchServiceApplication.class, args);
    }

    /*-----------------Resolving Locale-----------------*/
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
    /*-------------Changing Language with User Preference------------*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}


