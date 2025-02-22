package com.example.coffee_order.controller.ex;

import com.example.coffee_order.controller.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public Response handleRuntimeException(RuntimeException e) {
        return Response.fail(e.getMessage());
    }

}