package com.dukoia.microservice.service;

import com.dukoia.microservice.entities.Payment;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
@author Dukoia
@createTime 2020/11/3 11:08
*/
public interface PaymentService extends IService<Payment>{

    public Boolean isNull();
}
