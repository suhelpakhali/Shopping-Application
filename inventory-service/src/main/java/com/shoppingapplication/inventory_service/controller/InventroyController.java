package com.shoppingapplication.inventory_service.controller;

import com.shoppingapplication.inventory_service.dto.InventoryResponse;
import com.shoppingapplication.inventory_service.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventroyController {

     InventoryService inventoryService;

     // http://localhost:8082/api/inventory?skuCode=iphone-15&sku-code=iphone15-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){

        return inventoryService.isInStock(skuCode);
    }
}
