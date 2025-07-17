package com.ss8.controller;

import com.ss8.model.dto.ApiResponse;
import com.ss8.model.dto.DishDTO;
import com.ss8.model.entity.Dish;
import com.ss8.service.dish.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping
    public ResponseEntity<ApiResponse<Dish>> createDish(@ModelAttribute DishDTO dto) {
        Dish dish = dishService.createDish(dto);
        ApiResponse<Dish> response = new ApiResponse<>(dish, "Tạo món ăn thành công", true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Dish>> updateDish(@PathVariable Long id, @ModelAttribute DishDTO dto) {
        Dish updated = dishService.updateDish(id, dto);
        ApiResponse<Dish> response = new ApiResponse<>(updated, "Cập nhật món ăn thành công", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        ApiResponse<Void> response = new ApiResponse<>(null, "Xoá món ăn thành công", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Dish>>> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        ApiResponse<List<Dish>> response = new ApiResponse<>(dishes, "Lấy danh sách món ăn thành công", true);
        return ResponseEntity.ok(response);
    }
}
