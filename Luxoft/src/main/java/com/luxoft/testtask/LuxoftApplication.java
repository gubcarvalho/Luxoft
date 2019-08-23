package com.luxoft.testtask;

import java.time.Duration;
import java.util.Collections;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableJSONDoc
@EnableRetry
public class LuxoftApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuxoftApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    	
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		
    	RestTemplate restTemplate = restTemplateBuilder
    			.messageConverters(converter)
    			.setConnectTimeout(Duration.ofMinutes(1))
    			.setReadTimeout(Duration.ofMinutes(1))
    			.build();

    	return restTemplate;
    }

}
