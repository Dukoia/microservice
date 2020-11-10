package com.dukoia.microservice.enums;

/**
 * @author Dukoia
 * @createTime 2019/12/9 11:05
 */
public enum ResultCodeEnum {

    SUCCESS(200,"请求成功"),
    NULL_POINT(4001,"项目空指针异常"),
    ARITHMETIC(4002,"数学异常"),
    INDEX_OUT_BOUNDS(4003,"数组越界"),
    OUT_BOUNDS(4004,"自定义异常结果"),
    FAILED(400,"请求失败");

    private Integer code;

    private String message;

    ResultCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public Integer code(){
        return this.code;
    }
    public String message(){
        return this.message;
    }
}
