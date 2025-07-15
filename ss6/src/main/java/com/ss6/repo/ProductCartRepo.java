package com.ss6.repo;

import com.ss6.model.entity.ProductCart;
import com.ss6.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepo extends JpaRepository<ProductCart, Long>{
    @Query("SELECT pc FROM ProductCart pc WHERE pc.user = :user")
    List<ProductCart> findAllByUser(User user);
}
