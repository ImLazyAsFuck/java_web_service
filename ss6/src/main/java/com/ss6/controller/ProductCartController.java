package com.ss6.controller;

import com.ss6.model.dto.DataResponse;
import com.ss6.model.entity.ProductCart;
import com.ss6.model.entity.User;
import com.ss6.service.productcart.ProductCartService;
import com.ss6.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-carts")
@RequiredArgsConstructor
public class ProductCartController {

    private final ProductCartService productCartService;
    private final UserServiceImpl userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<DataResponse<List<ProductCart>>> getCartItemsByUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        List<ProductCart> cartItems = productCartService.getCartItemsByUser(user);
        return ResponseEntity.ok(new DataResponse<>(cartItems, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<ProductCart>> addToCart(@RequestBody ProductCart productCart) {
        ProductCart saved = productCartService.addToCart(productCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>(saved, HttpStatus.CREATED));
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<DataResponse<ProductCart>> updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        ProductCart updated = productCartService.updateQuantity(id, quantity);
        return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> removeFromCart(@PathVariable Long id) {
        productCartService.removeFromCart(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.NO_CONTENT));
    }
}
