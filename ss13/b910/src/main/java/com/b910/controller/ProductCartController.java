package com.b910.controller;

import com.b910.model.dto.request.CartRequest;
import com.b910.model.dto.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ProductCartController {

    private final ProductCartService productCartService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<?>> addToCart(@RequestBody CartRequest request) {
        productCartService.addToCart(request);
        return ResponseEntity.ok(new APIResponse<>(true, "Added to cart", null));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<APIResponse<?>> removeFromCart(@PathVariable Long productId) {
        productCartService.removeFromCart(productId);
        return ResponseEntity.ok(new APIResponse<>(true, "Removed from cart", null));
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<APIResponse<?>> updateQuantity(@PathVariable Long productId, @RequestBody QuantityUpdateRequest request) {
        productCartService.updateQuantity(productId, request.getQuantity());
        return ResponseEntity.ok(new APIResponse<>(true, "Quantity updated", null));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductCartDTO>>> viewCart() {
        List<ProductCartDTO> cart = productCartService.getUserCart();
        return ResponseEntity.ok(new APIResponse<>(true, "Cart retrieved", cart));
    }
}
