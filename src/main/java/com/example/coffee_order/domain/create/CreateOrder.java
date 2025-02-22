package com.example.coffee_order.domain.create;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class CreateOrder {
    private int StoreId;
    private int customerId;
    private Map<Integer, Integer> quantityByProduct; // ["아이스 아메리카노", 3]
}
