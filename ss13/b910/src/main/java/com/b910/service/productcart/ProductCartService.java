package com.b910.service.productcart;

import com.b910.model.dto.request.CartRequest;
import com.b910.model.entity.ProductCart;

public interface ProductCartService {
    void addToCart(CartRequest request);
    void removeFromCart(Long productId);
    void updateQuantity(Long productId, Integer quantity);
    List<ProductCart> getUserCart();
}
