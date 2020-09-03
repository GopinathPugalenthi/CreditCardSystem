package com.approval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.approval.client.UserClient;
import com.approval.entity.CustomerDetailEntity;
import com.approval.service.ICreditCardApprovalService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author javadevopsmc13 Controller class for Credit Card Approval Service
 *
 */
@RestController
@RequestMapping("/approveCreditCard")
@Slf4j
public class CreditCardApprovalController {

	@Autowired
	private ICreditCardApprovalService approvalService;
	
	@Autowired
	private UserClient client;

	/**
	 * This method is to update Approval status of Customer Details
	 * 
	 * @param id
	 * @param status
	 * @param approvedBy
	 * @throws NotFoundException
	 */
	@ApiOperation(value = "Credit for a customer", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Credit Card Application approved successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server Error") })
	@RequestMapping(value = "/{id}/{status}/{approvedBy}")
	public ResponseEntity<?> approve(@PathVariable("id") Integer id, @PathVariable("status") String status,
			@PathVariable("approvedBy") String approvedBy) throws NotFoundException {
		log.info("Credit Card Approval Service Create method");
		try {
			ResponseEntity<CustomerDetailEntity> customerDetail = client.getCustomerById(id);
			approvalService.updateCustomerDetails(id, status, approvedBy, customerDetail.getBody());
			return new ResponseEntity<>("Credit Card Approved", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
