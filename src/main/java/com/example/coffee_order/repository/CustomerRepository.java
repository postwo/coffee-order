package com.example.coffee_order.repository;

import com.example.coffee_order.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
