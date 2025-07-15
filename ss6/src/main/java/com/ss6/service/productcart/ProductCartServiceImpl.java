package com.ss6.service.productcart;

import com.ss6.model.entity.ProductCart;
import com.ss6.model.entity.User;
import com.ss6.repo.ProductCartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCartServiceImpl implements ProductCartService{

    private final ProductCartRepo productCartRepo;

    @Override
    public ProductCart addToCart(ProductCart productCart){
        return productCartRepo.save(productCart);
    }

    @Override
    public List<ProductCart> getCartItemsByUser(User user){
        return productCartRepo.findAllByUser(user);
    }

    @Override
    public ProductCart updateQuantity(Long id, Integer quantity){
        ProductCart existingProductCart = productCartRepo.findById(id).orElseThrow(() -> new RuntimeException("ProductCart not found with id: " + id));
        existingProductCart.setQuantity(quantity);
        return productCartRepo.save(existingProductCart);
    }

    @Override
    public void removeFromCart(Long id){
        productCartRepo.deleteById(id);
    }
}
