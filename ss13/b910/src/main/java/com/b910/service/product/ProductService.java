package com.b910.service.product;

import com.b910.model.entity.Product;

import java.util.List;

public interface ProductService{
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product editProduct(Product product);
    void deleteProduct(Long id);
}
