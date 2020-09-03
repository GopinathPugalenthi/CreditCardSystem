package com.approval.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.approval.entity.CustomerDetailEntity;

public interface ICreditCardApprovalService {

	public void updateCustomerDetails(Integer id, String status, String approvedBy, CustomerDetailEntity customerDetailEntity) throws NotFoundException;
}
