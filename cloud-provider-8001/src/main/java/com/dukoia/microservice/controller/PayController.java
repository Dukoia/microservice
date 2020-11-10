package com.dukoia.microservice.controller;

import com.dukoia.microservice.config.CMSException;
import com.dukoia.microservice.entities.CommentResult;
import com.dukoia.microservice.entities.Payment;
import com.dukoia.microservice.enums.ResultCodeEnum;
import com.dukoia.microservice.inter.ResponseResult;
import com.dukoia.microservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dukoia
 * @createTime 2020/11/3 11:09
 */
@RestController
@RequestMapping("/pay")
@Slf4j
@ResponseResult
public class PayController {

	@Autowired
	PaymentService paymentService;

	@GetMapping("/get/{id}")
	public Payment getPaymentById(@PathVariable("id") Long id){


		paymentService.isNull();
		Payment payment = paymentService.getById(id);
		return payment;
	}

	@PostMapping("create")
	public CommentResult create(@RequestBody Payment payment){

		boolean save = paymentService.save(payment);

		if (save)
			return new CommentResult(200,"获取成功",payment);
		else return new CommentResult(4001,"没有对应的订单",null);
	}
}
