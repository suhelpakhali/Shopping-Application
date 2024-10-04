package com.shoppingapplication.order_service.service;

import com.shoppingapplication.order_service.dto.InventoryResponse;
import com.shoppingapplication.order_service.dto.OrderLineItemsDto;
import com.shoppingapplication.order_service.dto.OrderRequest;
import com.shoppingapplication.order_service.model.Order;
import com.shoppingapplication.order_service.model.OrderLineItems;
import com.shoppingapplication.order_service.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    public String placeOrder(OrderRequest orderRequest){
        System.out.println(orderRequest);
        Order order= new Order();
order.setOrderNumber(UUID.randomUUID().toString());
List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDtoList().stream()
        .map(OrderLineItemsDto->MapToOrderLineItem(OrderLineItemsDto))
        .collect(Collectors.toList());

order.setOrderLineItemsList(orderLineItems);

// We have to need List of skuCode so that we can send to Inventory service to find out if it is in stock or not
List<String> skuCodes=order.getOrderLineItemsList().stream()
        .map(OrderLineItemsDto->OrderLineItemsDto.getSkuCode()).collect(Collectors.toList());


//Call Inventory Service, and place order if product is in
// stock
   InventoryResponse[] inventoryResponseArray=  webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder-> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductAreInStock=Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse->InventoryResponse.isInStock());
   if(allProductAreInStock){
       orderRepository.save(order);
       return "order placed";
   } else {
       throw new IllegalArgumentException("Product is not in stock please try again later");
   }

    }

    private OrderLineItems MapToOrderLineItem(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
