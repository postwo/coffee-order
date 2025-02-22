package com.example.coffee_order.repository;

import com.example.coffee_order.domain.StoreProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StoreProductRepository extends CrudRepository<StoreProduct,Integer> {
    Optional<StoreProduct> findByStoreIdAndProductId(int storeId, int productId); // 특정 매장에 상품에대한 값을 가지고 온다
}
