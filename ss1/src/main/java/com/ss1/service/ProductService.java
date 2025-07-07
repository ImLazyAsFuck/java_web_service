package com.ss1.service;

import com.ss1.entity.Product;

import java.util.List;

public interface ProductService{
    List<Product> findAll();
    Product findById(Long id);
    void save(Product product);
    void deleteById(Long id);
    void update(Product product);
}
