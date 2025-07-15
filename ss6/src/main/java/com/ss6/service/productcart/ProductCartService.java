package com.ss6.service.productcart;

import com.ss6.model.entity.ProductCart;
import com.ss6.model.entity.User;

import java.util.List;

public interface ProductCartService{
    List<ProductCart> getCartItemsByUser(User user);
    ProductCart addToCart(ProductCart productCart);
    ProductCart updateQuantity(Long id, Integer quantity);
    void removeFromCart(Long id);
}
