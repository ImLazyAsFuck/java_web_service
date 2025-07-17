package com.ss8.repo;

import com.ss8.model.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepo extends JpaRepository<Dish, Long>{
    Optional<Dish> findByName(String name);
}
