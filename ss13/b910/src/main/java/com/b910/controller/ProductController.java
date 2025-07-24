package com.b910.controller;

import com.b910.model.dto.response.APIResponse;
import com.b910.model.entity.Product;
import com.b910.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getProducts(){
        return ResponseEntity.ok(new APIResponse<>(true,"Get products successfully!", productService.getAllProducts(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<APIResponse<Product>> addProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(true,"Add product successfully!", productService.addProduct(product), HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<APIResponse<Product>> editProduct(@RequestBody Product product){
        return ResponseEntity.ok(new APIResponse<>(true,"Edit product successfully!", productService.editProduct(product), HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new APIResponse<>(true,"Delete product successfully!", null, HttpStatus.NO_CONTENT));
    }
}
