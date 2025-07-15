package com.ss6.service.product;

import com.ss6.model.dto.ProductPagination;
import com.ss6.model.entity.Product;
import com.ss6.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public ProductPagination getAllProducts(Pageable pageable, String search) {
        Page<Product> page = productRepo.findByNameContainingIgnoreCase(search, pageable);
        return new ProductPagination(
                page.getContent(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public Product updateProduct(Product product) {
        if (!productRepo.existsById(product.getId())) {
            throw new RuntimeException("Product not found with id: " + product.getId());
        }
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepo.deleteById(id);
    }
}
