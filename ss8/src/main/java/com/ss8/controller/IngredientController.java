package com.ss8.controller;

import com.ss8.model.dto.ApiResponse;
import com.ss8.model.entity.Ingredient;
import com.ss8.service.ingredient.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<ApiResponse<Ingredient>> create(
            @RequestPart Ingredient ingredient,
            @RequestPart MultipartFile image) throws IOException {
        Ingredient created = ingredientService.create(ingredient, image);
        return ResponseEntity.ok(new ApiResponse<>(created, "Ingredient created successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Ingredient>> update(
            @PathVariable Long id,
            @RequestPart Ingredient ingredient,
            @RequestPart(required = false) MultipartFile image) throws IOException {
        Ingredient updated = ingredientService.update(id, ingredient, image);
        return ResponseEntity.ok(new ApiResponse<>(updated, "Ingredient updated successfully", true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        ingredientService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(null, "Ingredient deleted successfully", true));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Ingredient>>> getAll() {
        List<Ingredient> ingredients = ingredientService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(ingredients, "List of ingredients", true));
    }
}
