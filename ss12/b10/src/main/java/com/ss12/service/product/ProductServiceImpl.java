package com.ss12.service.product;

import com.ss12.model.dto.request.ProductRequestDTO;
import com.ss12.model.entity.Product;
import com.ss12.model.mapper.ProductMapper;
import com.ss12.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product create(ProductRequestDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        return productRepo.save(product);
    }

    @Override
    public Product update(Long id, ProductRequestDTO dto) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductMapper.updateEntity(product, dto);
        return productRepo.save(product);
    }

    @Override
    public void delete(Long id) {
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepo.deleteById(id);
    }
}
