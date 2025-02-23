package com.example.coffee_order.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@Table(name = "stores_products")
public class StoreProduct {
    @Id
    private int storeProductId;

    @Column
    private int storeId;

    @Column
    private int productId;

    @Column
    private int stockQuantity;

    //재고 수량 감소
    public void adjustStockQuantity (int buyQuantity) {
        if (stockQuantity < buyQuantity){
            throw new RuntimeException("재고보다 많을 수 없습니다");
        }
        this.stockQuantity = this.stockQuantity - buyQuantity;
    }
}