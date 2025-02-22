package com.example.coffee_order.repository;

import com.example.coffee_order.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
