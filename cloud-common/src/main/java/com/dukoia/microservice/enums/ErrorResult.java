package com.dukoia.microservice.enums;

import java.io.Serializable;

/**
 * @author Dukoia
 * @createTime 2020/11/3 16:34
 */
public class ErrorResult implements Serializable {

	private Integer code;

	private String message;

	public ErrorResult(Integer code,String message){
		this.code = code;
		this.message = message;
	}

	public ErrorResult(){
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
