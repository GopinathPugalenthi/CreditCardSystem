package com.applycreditcard.repository;

import org.springframework.data.repository.CrudRepository;

import com.applycreditcard.entity.CustomerDetailEntity;

public interface CreditCardRepository extends CrudRepository<CustomerDetailEntity, Integer>{

}
