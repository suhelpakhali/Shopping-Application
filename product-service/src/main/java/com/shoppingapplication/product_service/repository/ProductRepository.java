package com.shoppingapplication.product_service.repository;

import com.shoppingapplication.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

}
