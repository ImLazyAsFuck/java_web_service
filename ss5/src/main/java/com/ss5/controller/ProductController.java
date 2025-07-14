package com.ss5.controller;

import com.ss5.model.ProductList;
import com.ss5.model.dto.DataResponse;
import com.ss5.model.entity.Product;
import com.ss5.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<DataResponse<Product>> create(@Valid @RequestBody Product product) {
        Product saved = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DataResponse.of(saved, HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAll() {
        List<Product> list = productService.getAllProducts();
        return ResponseEntity.ok(DataResponse.of(list, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> getById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(DataResponse.of(product, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        return ResponseEntity.ok(DataResponse.of(updated, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(DataResponse.of("Deleted successfully", HttpStatus.OK));
    }

    @GetMapping(value = "/list.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<DataResponse<ProductList>> getAllXml() {
        List<Product> list = productService.getAllProducts();
        return ResponseEntity.ok(DataResponse.of(new ProductList(list), HttpStatus.OK));
    }
}
