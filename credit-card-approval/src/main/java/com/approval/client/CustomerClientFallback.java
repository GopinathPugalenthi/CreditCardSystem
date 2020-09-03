package com.approval.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.approval.entity.CustomerDetailEntity;

@Component
public class CustomerClientFallback implements UserClient{

	@Override
	public ResponseEntity<CustomerDetailEntity> getCustomerById(int id) {
		return ResponseEntity.ok().build();
	}

}
