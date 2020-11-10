package com.dukoia.microservice.config;

import com.dukoia.microservice.handler.ResponseResultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1.注册HandlerInterceptor的实现ResponseResultInterceptor
 * @author Dukoia
 * @createTime 2020/11/9 9:07
 */
@EnableWebMvc
@Configuration
public class ApplicationContextConfig implements WebMvcConfigurer {

	@Bean
	public ResponseResultInterceptor responseResultInterceptor(){
		return new ResponseResultInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(responseResultInterceptor());
	}

}
