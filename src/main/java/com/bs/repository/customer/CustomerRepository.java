package com.bs.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bs.model.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
