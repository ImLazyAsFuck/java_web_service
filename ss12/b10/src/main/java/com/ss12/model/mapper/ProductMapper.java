package com.ss12.model.mapper;

import com.ss12.model.dto.request.ProductRequestDTO;
import com.ss12.model.dto.response.ProductResponseDTO;
import com.ss12.model.entity.Product;

public class ProductMapper {
    public static ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getStock(),
                product.getDescription(),
                product.getImage()
        );
    }

    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        updateEntity(product, dto);
        return product;
    }

    public static void updateEntity(Product product, ProductRequestDTO dto) {
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
    }
}
