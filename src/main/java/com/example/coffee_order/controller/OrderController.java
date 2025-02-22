package com.example.coffee_order.controller;

import com.example.coffee_order.domain.create.CreateOrder;
import com.example.coffee_order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/order")
    public Response<Integer> newOrder() {
        HashMap<Integer, Integer> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put(1, 10); // key 상품 아이디 ,value 주문한 갯수

        orderService.order(CreateOrder.builder()
                .customerId(1)
                .quantityByProduct(objectObjectHashMap)
                .build());

        return Response.success(null);
    }
}
