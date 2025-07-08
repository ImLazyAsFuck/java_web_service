package com.ss2.repo;

import com.ss2.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByNameContainingIgnoreCase(String name);
    List<Theater> findByAddressContainingIgnoreCase(String address);
}
