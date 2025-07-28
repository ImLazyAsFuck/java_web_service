package com.productmanagement.repo;

import com.productmanagement.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long>{
    List<Product> findByStockGreaterThan(int stock);

    List<Product> findByYearGreaterThanEqual(int year);

    List<Product> findByCategoryId(Long categoryId);
}
