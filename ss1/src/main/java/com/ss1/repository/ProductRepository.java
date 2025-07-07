package com.ss1.repository;

import com.ss1.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository{
    List<Product> findAll();
    Product findById(Long id);
    void save(Product product);
    void deleteById(Long id);
    void update(Product product);
    Optional<Product> findByName(String name);
    Optional<Product> findByNameNotIncludeId(String name, Long id);
}
