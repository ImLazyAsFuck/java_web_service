package com.ss1.service;

import com.ss1.entity.Product;
import com.ss1.repository.ProductRepository;
import com.ss1.validation.customerException.FieldValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public List<Product> findAll(){
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            return new ArrayList<>();
        }
        return products;
    }

    @Override
    public Product findById(Long id){
        Product product = productRepository.findById(id);
        if(product == null){
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        return product;
    }

    @Override
    public void save(Product product){
        if(productRepository.findByName(product.getName()).isPresent()){
            throw new FieldValidationException("name", "Product already exists with name: " + product.getName());
        }
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id){
        if(productRepository.findById(id) == null){
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public void update(Product product){
        if(productRepository.findById(product.getId()) == null){
            throw new FieldValidationException("id", "Product not found with id: " + product.getId());
        }

        if(productRepository.findByNameNotIncludeId(product.getName(), product.getId()).isPresent()){
            throw new FieldValidationException("name", "Product already exists with name: " + product.getName());
        }

        productRepository.update(product);
    }
}
