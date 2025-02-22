package com.example.coffee_order.controller;

import com.example.coffee_order.domain.create.CreateCustomer;
import com.example.coffee_order.domain.dto.CustomerDto;
import com.example.coffee_order.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //localhost:8080/api/v1/customers?name=danny&address=seoul&phonenNmber=010-1111-1111
    //@RequestParam URL 쿼리 파라미터 또는 x-www-form-urlencoded 형식의 폼 데이터를 처리
    // JSON 형식의 데이터를 처리합니다. HTTP 요청 본문에 포함된 JSON 데이터를 Java 객체로 변환하여 사용
    @PostMapping("/api/v1/customer")
    public Response<CustomerDto> createNewCustomer(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ) {
        return Response.success(customerService.newCustomer(CreateCustomer
                .builder()
                .address(address)
                .name(name)
                .phoneNumber(phoneNumber)
                .build())
        );
    }
}
