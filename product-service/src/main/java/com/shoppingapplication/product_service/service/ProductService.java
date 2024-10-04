package com.shoppingapplication.product_service.service;

import com.shoppingapplication.product_service.dto.ProductRequest;
import com.shoppingapplication.product_service.dto.ProductResponse;
import com.shoppingapplication.product_service.model.Product;
import com.shoppingapplication.product_service.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private  ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
Product product= Product.builder()
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .build();

        productRepository.save(product);
    log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products=productRepository.findAll();

        return products.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
