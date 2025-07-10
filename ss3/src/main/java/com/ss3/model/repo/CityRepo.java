package com.ss3.model.repo;

import com.ss3.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<City, Long>{
    boolean existsByNameAndCountryId(String name, Long countryId);

    List<City> findByNameContainingIgnoreCase(String keyword);
}
