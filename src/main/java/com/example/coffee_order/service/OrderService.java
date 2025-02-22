package com.example.coffee_order.service;

import com.example.coffee_order.domain.Order;
import com.example.coffee_order.domain.create.CreateOrder;
import com.example.coffee_order.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void order(CreateOrder createOrder) {
        Order entity = Order.newOrder(createOrder);
        orderRepository.save(entity);
    }
}