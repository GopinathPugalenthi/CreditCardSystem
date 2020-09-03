package com.approval.repository;

import org.springframework.data.repository.CrudRepository;

import com.approval.entity.CustomerDetailEntity;

public interface ApprovalCreditCardRepository extends CrudRepository<CustomerDetailEntity, Integer>{

}
