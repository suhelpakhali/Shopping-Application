package com.shoppingapplication.order_service.controller;

import com.shoppingapplication.order_service.dto.OrderRequest;
import com.shoppingapplication.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@Slf4j
public class OrderController {

private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        log.info("Order is {}",orderRequest);
        if(orderRequest==null){
            return"orderRequest is null";
        }
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
}
