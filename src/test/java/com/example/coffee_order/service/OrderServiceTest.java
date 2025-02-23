package com.example.coffee_order.service;

import com.example.coffee_order.domain.StoreProduct;
import com.example.coffee_order.domain.create.CreateOrder;
import com.example.coffee_order.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // mockito 사용위해 추가
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    StoreService storeService;

    @InjectMocks // 위에 두개 Mock 처리를 한 값을 orderService의 주입을 하겠다는 의미이다
    OrderService orderService;

    @Test
    @DisplayName("구매 수량이 재고 수량보다 적을 때, 정상 주문이 가능하다")
    public void stockQuantityTest_success() {
        // given
        int buyQuantity = 5; //몇개를 살건지
        int stockQuantity = 50; // 재고

        HashMap<Integer,Integer> map = new HashMap<>();

        map.put(1,buyQuantity);

        CreateOrder createOrder = CreateOrder.builder()
                .StoreId(1)
                .customerId(1)
                .quantityByProduct(map)
                .build();

        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();

        when(storeService.getStoreProduct(1,1))
                .thenReturn(stock);

        // when
        orderService.newOrder(createOrder);

        // then
        assertThat(stock.getStockQuantity()).isEqualTo(stockQuantity-buyQuantity);
    }

    @Test
    @DisplayName("구매 수량이 재고 수량보다 많을 때, 정상 주문이 불가능하다")
    public void stockQuantityTest_fail() {
        // given
        int buyQuantity = 100; //몇개를 살건지
        int stockQuantity = 50; // 재고

        HashMap<Integer,Integer> map = new HashMap<>();

        map.put(1,buyQuantity);

        CreateOrder createOrder = CreateOrder.builder()
                .StoreId(1)
                .customerId(1)
                .quantityByProduct(map)
                .build();

        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();

        when(storeService.getStoreProduct(1,1))
                .thenReturn(stock);

        // when& then
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> orderService.newOrder(createOrder));

        assertThat(runtimeException.getMessage()).isEqualTo("재고가 없습니다");
    }
}