package com.dukoia.microservice.entities;

import com.dukoia.microservice.enums.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dukoia
 * @createTime 2020/11/3 11:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResult<T> implements Serializable {

	private Integer code;

	private String message;

	private Object data;

	public CommentResult(Integer code, String message){
		this(code, message, null);
	}

	public static CommentResult success(Object data){
		CommentResult<Object> result = new CommentResult<>();
		result.setCode(200);
		result.setMessage("请求成功");
		result.setData(data);
		return result;
	}

	public static CommentResult failure(ErrorResult data){
		CommentResult<Object> result = new CommentResult<>();
		result.setCode(data.getCode());
		result.setMessage(data.getMessage());
		return result;
}
}
