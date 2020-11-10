package com.dukoia.microservice.service.impl;

import com.dukoia.microservice.config.CMSException;
import com.dukoia.microservice.enums.ResultCodeEnum;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dukoia.microservice.mapper.PaymentMapper;
import com.dukoia.microservice.entities.Payment;
import com.dukoia.microservice.service.PaymentService;
/**
@author Dukoia
@createTime 2020/11/3 11:08
*/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService{

	@Override
	public Boolean isNull() {
		if (1==1){
			throw new CMSException(ResultCodeEnum.OUT_BOUNDS);
		}
		return false;
	}
}
