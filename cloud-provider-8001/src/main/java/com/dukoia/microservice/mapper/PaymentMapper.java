package com.dukoia.microservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dukoia.microservice.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
@author Dukoia
@createTime 2020/11/3 11:08
*/
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}