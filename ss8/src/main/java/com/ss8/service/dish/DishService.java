package com.ss8.service.dish;

import com.ss8.model.dto.DishDTO;
import com.ss8.model.entity.Dish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService{
    Dish findByName(String name);
    Dish createDish(DishDTO dto);
    Dish updateDish(Long id, DishDTO dto);
    void deleteDish(Long id);
    List<Dish> getAllDishes();
}
