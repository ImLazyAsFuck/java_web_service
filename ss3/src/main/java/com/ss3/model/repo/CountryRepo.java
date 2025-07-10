package com.ss3.model.repo;

import com.ss3.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepo extends JpaRepository<Country, Long>{
    boolean existsByName(String name);
    List<Country> findByNameContainingIgnoreCase(String keyword);
}
