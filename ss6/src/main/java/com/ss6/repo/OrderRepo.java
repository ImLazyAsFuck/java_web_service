package com.ss6.repo;

import com.ss6.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{
    List<Order> findAllByCreatedAt(LocalDate createdAt);
    List<Order> findByCreatedAtBetween(LocalDate start, LocalDate end);
}
