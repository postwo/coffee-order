package com.example.coffee_order.domain;

import com.example.coffee_order.domain.create.CreateOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Table(name = "orders")
public class Order {
    @Id
    private int orderId;

    @Column
    private int customerId;

    @Column
    private Timestamp orderedAt;

    //@MappedCollection(idColumn = "order_item_id", keyColumn = "order_id")는 Order 객체가 여러 개의 OrderItem을 포함할 수 있게 설정하는 방식
    @MappedCollection(idColumn = "order_id", keyColumn = "order_item_id")
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order(int customerId, Set<OrderItem> orderItems) {
        this.customerId = customerId;
        this.orderedAt = Timestamp.valueOf(LocalDateTime.now());
        this.orderItems = orderItems;
    }


    public static Order newOrder(CreateOrder createOrder) {
        Set<OrderItem> items = new HashSet<>();

        for (Map.Entry<Integer, Integer> entry : createOrder.getQuantityByProduct().entrySet()) {
            items.add(new OrderItem(entry.getKey(), entry.getValue()));
        }

        return new Order(createOrder.getCustomerId(), items);
    }


}
