package com.example.coffee_order.controller;

import com.example.coffee_order.domain.create.CreateOrder;
import com.example.coffee_order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/order")
    public Response<Integer> newOrder(
            @RequestBody NewOrderRequest request
    ) {
        orderService.newOrder(CreateOrder.builder()
                .customerId(request.getCustomerId())
                .quantityByProduct(request.getProducts())
                .StoreId(request.getStoreId())
                .build());

        return Response.success(null);
    }
}
