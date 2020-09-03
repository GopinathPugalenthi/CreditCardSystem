package com.approval.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.approval.entity.CustomerDetailEntity;

@FeignClient(url = "http://localhost:8081/applyCreditCard", name = "UserClient", fallback = CustomerClientFallback.class)
public interface UserClient {
	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerDetailEntity> getCustomerById(@PathVariable int id);
}