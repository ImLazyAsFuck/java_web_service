package com.ss6.service.product;

import com.ss6.model.dto.ProductPagination;
import com.ss6.model.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService{
    ProductPagination getAllProducts(Pageable pageable, String search);
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
}
