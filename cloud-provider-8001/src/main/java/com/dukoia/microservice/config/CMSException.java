package com.dukoia.microservice.config;

import com.dukoia.microservice.enums.ResultCodeEnum;

/**
 * 自定义异常
 * @author Dukoia
 * @createTime 2020/11/9 9:33
 */
public class CMSException extends RuntimeException {


	private Integer code;

	public Integer getCode() {
		return code;
	}

	public CMSException(Integer code, String message){
		super(message);
		this.code = code;
	}

	public CMSException(ResultCodeEnum resultCodeEnum){
		super(resultCodeEnum.message());
		this.code = resultCodeEnum.code();
	}
}
