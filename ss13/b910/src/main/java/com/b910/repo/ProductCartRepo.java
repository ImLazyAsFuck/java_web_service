package com.b910.repo;

import com.b910.model.entity.Product;
import com.b910.model.entity.ProductCart;
import com.b910.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCartRepo extends JpaRepository<ProductCart, Long>{
    List<ProductCart> findAllByUserId(UUID userId);
    Optional<ProductCart> findByUserAndProduct(User user, Product product);
    Optional<ProductCart> findByUserIdAndProductId(UUID userId, Long productId);
    void deleteByUserIdAndProductId(UUID userId, Long productId);
}
