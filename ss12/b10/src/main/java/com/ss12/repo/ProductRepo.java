package com.ss12.repo;

import com.ss12.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long>{
}
