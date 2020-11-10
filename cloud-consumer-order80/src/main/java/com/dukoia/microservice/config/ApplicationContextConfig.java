package com.dukoia.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dukoia
 * @createTime 2020/11/4 10:02
 */
@Configuration
public class ApplicationContextConfig {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
