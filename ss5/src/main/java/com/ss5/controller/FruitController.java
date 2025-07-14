package com.ss5.controller;

import com.ss5.model.dto.DataResponse;
import com.ss5.model.entity.Fruit;
import com.ss5.service.fruit.FruitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fruits")
@RequiredArgsConstructor
public class FruitController{

    private final FruitService fruitService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Fruit>>> getAll(){
        List<Fruit> list = fruitService.getAllFruits();
        return ResponseEntity.ok(DataResponse.of(list, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Fruit>> create(@Valid @RequestBody Fruit fruit) {
        Fruit saved = fruitService.createFruit(fruit);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DataResponse.of(saved, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Fruit>> getById(@PathVariable Long id) {
        Fruit fruit = fruitService.getFruitById(id);
        return ResponseEntity.ok(DataResponse.of(fruit, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Fruit>> update(@PathVariable Long id, @Valid @RequestBody Fruit fruit) {
        Fruit updated = fruitService.updateFruit(id, fruit);
        return ResponseEntity.ok(DataResponse.of(updated, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> delete(@PathVariable Long id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.ok(DataResponse.of("Deleted successfully", HttpStatus.OK));
    }
}
