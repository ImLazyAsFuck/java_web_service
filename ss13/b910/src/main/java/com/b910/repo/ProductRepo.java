package com.b910.repo;

import com.b910.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepo extends JpaRepository<Product, Long>{
}
