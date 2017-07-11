package com.stackroute.swisit.searcher;


import java.util.Locale;
import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@EnableMongoRepositories
@EnableEurekaClient
@EnableAsync
//@EnableRedisRepositories
public class SwisitApplication extends CachingConfigurerSupport{

	
	public static void main(String[] args) {
				
		SpringApplication.run(SwisitApplication.class, args);
	}
	
	/* Jedis ConnectionFactory */
	/*@Bean
	RedisConnectionFactory connectionFactory()
	{
		return new JedisConnectionFactory();
	}

     redis template definition 
	@Bean
	 RedisTemplate< String, Object > redisTemplate() {
	  final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
	  template.setConnectionFactory( connectionFactory() );
	  template.setExposeConnection(true);
	  template.setKeySerializer( new StringRedisSerializer() );
	  template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  return template;
	 }
*/	
	
//	 /* declare Redis Cache Manager */
//    @Bean(name="cacheManager")
//    @Override
//    public RedisCacheManager cacheManager()
//    {
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//        redisCacheManager.setTransactionAware(true);
//        redisCacheManager.setLoadRemoteCachesOnStartup(true);
//        redisCacheManager.setUsePrefix(true);
//        return redisCacheManager;
//    }
	/* Defining the default language for Internationalization */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
     slr.setDefaultLocale(Locale.US);
        return slr;
    }
    
    /* Specifying the message path for internationalization */
    @Bean
    public MessageSource messageSource() {
         ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
         messageSource.setBasename("classpath:/messages/messages");
         return messageSource;
    }
    
    /* specifying the parameter name for chainging language */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    /* Executor calls the Async method */
    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("-");
        executor.initialize();
        return executor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
		
	}

