package com.shoppingapplication.inventory_service.service;

import com.shoppingapplication.inventory_service.dto.InventoryResponse;
import com.shoppingapplication.inventory_service.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
@Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
       return inventoryRepository.findBySkuCodeIn(skuCode).stream()
               .map(inventory->
                   InventoryResponse.builder()
                           .isInStock(inventory.getQuantity()>0)
                           .skuCode(inventory.getSkuCode())
                           .build()
               ).collect(Collectors.toList());



    }
}
