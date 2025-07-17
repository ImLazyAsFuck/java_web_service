package com.ss8.repo;

import com.ss8.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    List<Customer> findByStatusTrue();
}
