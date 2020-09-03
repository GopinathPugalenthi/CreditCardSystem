package com.applycreditcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applycreditcard.entity.CustomerDetailEntity;
import com.applycreditcard.model.CustomerModel;
import com.applycreditcard.service.IApplyCreditCardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author javadevopsmc13 Controller class for apply credit card service
 *
 */
@RestController
@RequestMapping("/applyCreditCard")
@Slf4j
public class ApplyCreditCardController {

	@Autowired
	private IApplyCreditCardService creditCardService;

	/**
	 * This method is used to save Customer details to the database
	 * 
	 * @param customerDetails
	 */
	@ApiOperation(value = "Apply credit card for a customer", response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Customer details saved successfully"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody CustomerModel customerDetails) {
		log.info("Apply Credit Card Service Create method");
		try {
			creditCardService.saveCustomerDetails(customerDetails);
			return new ResponseEntity<>("Customer details saved successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * This method is used to find a customer.
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	@ApiOperation(value = "Find Customer Details", response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Customer details fetched"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDetailEntity> getCustomerById(@PathVariable int id) throws NotFoundException {
		CustomerDetailEntity customerDetails= creditCardService.findCustomer(id);
		return new ResponseEntity<>(customerDetails, HttpStatus.OK);
	}

}
