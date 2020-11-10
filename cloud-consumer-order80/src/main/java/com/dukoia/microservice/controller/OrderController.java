package com.dukoia.microservice.controller;

import com.dukoia.microservice.entities.CommentResult;
import com.dukoia.microservice.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dukoia
 * @createTime 2020/11/4 10:00
 */

@RestController
public class OrderController {

	public static final String PAYMENT_URL = "http://127.0.0.1:8001";

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/consumer/payment/get/{id}")
	public CommentResult getOrder(@PathVariable("id") Long id){
		return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommentResult.class);
	}

	@PostMapping("/consumer/create")
	public CommentResult create(Payment payment){
		return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment, CommentResult.class);
	}
}
