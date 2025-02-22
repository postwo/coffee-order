package com.example.coffee_order.service;

import com.example.coffee_order.domain.Order;
import com.example.coffee_order.domain.StoreProduct;
import com.example.coffee_order.domain.create.CreateOrder;
import com.example.coffee_order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final StoreService storeService;

    public OrderService(OrderRepository orderRepository, StoreService storeService) {
        this.orderRepository = orderRepository;
        this.storeService = storeService;
    }

    public void newOrder(CreateOrder createOrder) {
        List<StoreProduct> storeProducts = new ArrayList<>();

        for (Map.Entry<Integer,Integer> entry :createOrder.getQuantityByProduct().entrySet()){
            Integer productId = entry.getKey();
            Integer buyQuantity = entry.getValue(); // 상품당 몇개씩 구매할려고 하는지

            StoreProduct storeProduct = storeService.getStoreProduct(
                    createOrder.getStoreId(),
                    productId
            );

            int stockQuantity = storeProduct.getStockQuantity();

            if (buyQuantity > stockQuantity) {
                throw new RuntimeException("재고가 없습니다");
            }

            storeProduct.adjustStockQuantity(buyQuantity);
            storeProducts.add(storeProduct);
        }

        Order entity = Order.newOrder(createOrder);
        orderRepository.save(entity);
        storeService.saveAll(storeProducts);
    }
}