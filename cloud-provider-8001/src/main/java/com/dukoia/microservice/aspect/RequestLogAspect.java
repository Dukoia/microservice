package com.dukoia.microservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * AOP实现对请求接口的日志记录
 * 1.前置通知 doBefore(JoinPoint joinPoint)
 * 2.环绕通知 doAround(ProceedingJoinPoint proceedingJoinPoint)
 * 3.异常通知 doAfterThrow(JoinPoint joinPoint, RuntimeException e)
 * @author Dukoia
 * @createTime 2020/10/23 9:20
 */
@Component
@Aspect
public class RequestLogAspect {
	private final static Logger LOGGER = LoggerFactory.getLogger(RequestLogAspect.class);

	@Pointcut("execution(* com.dukoia.microservice.controller..get*(..))")
	public void requestServer() {
	}

	@Before("requestServer()")
	public void doBefore(JoinPoint joinPoint) {

		LOGGER.info("============记录请求日志=============");

	}

	@Around("requestServer()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		RequestInfo requestInfo = new RequestInfo();
		requestInfo.setIp(request.getRemoteAddr());
		requestInfo.setUrl(request.getRequestURL().toString());
		requestInfo.setHttpMethod(request.getMethod());
		requestInfo.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
				proceedingJoinPoint.getSignature().getName()));
		requestInfo.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
		requestInfo.setResult(result);
		requestInfo.setTimeCost(System.currentTimeMillis() - start);
//		LOGGER.info("Request Info      : {}", JSON.toJSONString(requestInfo));

		return result;
	}


	@AfterThrowing(pointcut = "requestServer()", throwing = "e")
	public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {

		LOGGER.info("请求失败失败原因",e);
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		RequestInfo requestErrorInfo = new RequestInfo();
		requestErrorInfo.setIp(request.getRemoteAddr());
		requestErrorInfo.setUrl(request.getRequestURL().toString());
		requestErrorInfo.setHttpMethod(request.getMethod());
		requestErrorInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName()));
		requestErrorInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
		requestErrorInfo.setErrorMsg(e.getMessage());
//		LOGGER.info("Error Request Info      : {}", JSON.toJSONString(requestErrorInfo));
	}

	/**
	 * 获取入参
	 * @param proceedingJoinPoint
	 *
	 * @return
	 * */
	private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
		//参数名
		String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
		//参数值
		Object[] paramValues = proceedingJoinPoint.getArgs();

		return buildRequestParam(paramNames, paramValues);
	}

	private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
		//参数名
		String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
		//参数值
		Object[] paramValues = joinPoint.getArgs();

		return buildRequestParam(paramNames, paramValues);
	}

	private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
		Map<String, Object> requestParams = new HashMap<>();
		for (int i = 0; i < paramNames.length; i++) {
			Object value = paramValues[i];

			//如果是文件对象
			if (value instanceof MultipartFile) {
				MultipartFile file = (MultipartFile) value;
				value = file.getOriginalFilename();  //获取文件名
			}

			requestParams.put(paramNames[i], value);
		}

		return requestParams;
	}

	public class RequestInfo {
		private String ip;
		private String url;
		private String httpMethod;
		private String classMethod;
		private Object requestParams;
		private Object result;
		private Long timeCost;
		private String errorMsg;

		public String getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHttpMethod() {
			return httpMethod;
		}

		public void setHttpMethod(String httpMethod) {
			this.httpMethod = httpMethod;
		}

		public String getClassMethod() {
			return classMethod;
		}

		public void setClassMethod(String classMethod) {
			this.classMethod = classMethod;
		}

		public Object getRequestParams() {
			return requestParams;
		}

		public void setRequestParams(Object requestParams) {
			this.requestParams = requestParams;
		}

		public Object getResult() {
			return result;
		}

		public void setResult(Object result) {
			this.result = result;
		}

		public Long getTimeCost() {
			return timeCost;
		}

		public void setTimeCost(Long timeCost) {
			this.timeCost = timeCost;
		}
	}

}
