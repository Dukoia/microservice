package com.dukoia.microservice.handler;

import com.dukoia.microservice.inter.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Dukoia
 * @createTime 2020/11/3 16:12
 */
@Slf4j
public class ResponseResultInterceptor implements HandlerInterceptor {

	public static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		log.info("=========ResponseResultInterceptor In============");
		//获取请求中的标识注解，用作对返回结果的封装
		if (handler instanceof HandlerMethod){
			final  HandlerMethod handlerMethod = (HandlerMethod)handler;
			final  Class<?> clazz = handlerMethod.getBeanType();
			final  Method method = handlerMethod.getMethod();
			if (clazz.isAnnotationPresent(ResponseResult.class)){
				request.setAttribute(RESPONSE_RESULT_ANN,clazz.getAnnotation(ResponseResult.class));
			}else if(method.isAnnotationPresent(ResponseResult.class)){
				request.setAttribute(RESPONSE_RESULT_ANN,method.getAnnotation(ResponseResult.class));
			}
		}
		return true;
	}
}
