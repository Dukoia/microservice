package com.dukoia.microservice.handler;

import com.dukoia.microservice.config.CMSException;
import com.dukoia.microservice.enums.ErrorResult;
import com.dukoia.microservice.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

/**
 * 全局异常处理
 * @author Dukoia
 * @createTime 2020/10/23 9:52
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorResult error(Exception e){
		ErrorResult result = new ErrorResult();
		result.setCode(ResultCodeEnum.FAILED.code());
		result.setMessage(ResultCodeEnum.FAILED.message());
		return result;
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public ErrorResult nullPoint(NullPointerException e){
		ErrorResult result = new ErrorResult();
		result.setCode(ResultCodeEnum.NULL_POINT.code());
		result.setMessage(ResultCodeEnum.NULL_POINT.message());
		return result;
	}

	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public ErrorResult nullPoint(ArithmeticException e){
		ErrorResult result = new ErrorResult();
		result.setCode(ResultCodeEnum.ARITHMETIC.code());
		log.info("GlobalExceptionHandler:{}",e.getMessage());
		result.setMessage(ResultCodeEnum.ARITHMETIC.message());

		return result;
	}

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseBody
	public ErrorResult nullPoint(IndexOutOfBoundsException e){
		ErrorResult result = new ErrorResult();
		result.setCode(ResultCodeEnum.INDEX_OUT_BOUNDS.code());
		result.setMessage(ResultCodeEnum.INDEX_OUT_BOUNDS.message());

		return result;
	}

	@ExceptionHandler(CMSException.class)
	@ResponseBody
	public ErrorResult nullPoint(CMSException e){
		ErrorResult result = new ErrorResult();
		result.setCode(e.getCode());
		result.setMessage(e.getMessage());

		return result;
	}



}
