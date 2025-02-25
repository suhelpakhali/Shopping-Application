package com.shoppingapplication.product_service.controller;

import com.shoppingapplication.product_service.dto.ProductRequest;
import com.shoppingapplication.product_service.dto.ProductResponse;
import com.shoppingapplication.product_service.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        try {
            productService.createProduct(productRequest);
        }
        catch(Exception e){
            log.error("Exception creating {}",productRequest.getName());
        }
        log.info("info : product saved is {}: ", productRequest.getName());

        /*log.info("info : product saved is {}: ", productRequest.getName());
        log.error("error");
        log.warn("warn");
        log.debug("debug");
        log.trace("trace");
*/
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productResponses = new ArrayList<>();
        try {
            log.info("Entered getAllProductMethod");
            productResponses = productService.getAllProducts();
        } catch (Exception e) {
            log.error("List of products is not found" + e);
        }

        return productResponses;

    }
}
