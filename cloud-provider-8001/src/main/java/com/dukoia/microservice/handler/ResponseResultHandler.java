package com.dukoia.microservice.handler;

import com.dukoia.microservice.entities.CommentResult;
import com.dukoia.microservice.enums.ErrorResult;
import com.dukoia.microservice.inter.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dukoia
 * @createTime 2020/11/3 16:22
 */
//@Component
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

	public static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
		//获取请求中的注解，决定返回结果是否需要被包装
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		ResponseResult attribute = (ResponseResult)request.getAttribute(RESPONSE_RESULT_ANN);
		return attribute == null ? false :true;
	}

	@Override
	public Object beforeBodyWrite(Object body,
								  MethodParameter methodParameter,
								  MediaType mediaType,
								  Class<? extends HttpMessageConverter<?>> aClass,
								  ServerHttpRequest serverHttpRequest,
								  ServerHttpResponse serverHttpResponse) {

		log.info("===ResponseResultHandler In===");
		//判断返回结果是否被异常处理器处理
		if (body instanceof ErrorResult){
			ErrorResult result = (ErrorResult)body;
			return CommentResult.failure(result);
		}
		return CommentResult.success(body);
	}
}
