package com.ss5.service.product;

import com.ss5.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Long id, Product dto);

    void deleteProduct(Long id);
}
