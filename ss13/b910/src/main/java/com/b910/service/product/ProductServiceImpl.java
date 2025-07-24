package com.b910.service.product;

import com.b910.model.entity.Product;
import com.b910.repo.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @Override
    public Product addProduct(Product product){
        return productRepo.save(product);
    }

    @Override
    public Product editProduct(Product product){
        if(productRepo.existsById(product.getId())){
            return productRepo.save(product);
        }
        throw new EntityNotFoundException("Khong ton tai id");
    }

    @Override
    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }
}
