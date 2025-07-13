package com.ss4.model.mapper;

import com.ss4.model.dto.CategoryDTO;
import com.ss4.model.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryDTO dto) {
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public static CategoryDTO toDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
