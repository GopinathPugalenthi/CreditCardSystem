package com.applycreditcard.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.applycreditcard.entity.CustomerDetailEntity;
import com.applycreditcard.model.CustomerModel;

public interface IApplyCreditCardService {

	public void saveCustomerDetails(CustomerModel customerDetail);

	public CustomerDetailEntity findCustomer(int id) throws NotFoundException;

}
