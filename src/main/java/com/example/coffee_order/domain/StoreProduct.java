package com.example.coffee_order.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "storeProducts")
public class StoreProduct {
    @Id
    private int storeProductId;

    @Column
    private int storeId;

    @Column
    private int productId;

    @Column
    private int stockQuantity;
}