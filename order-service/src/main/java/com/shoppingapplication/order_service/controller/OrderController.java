package com.shoppingapplication.order_service.controller;

import com.shoppingapplication.order_service.dto.OrderRequest;
import com.shoppingapplication.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
}
