package com.ss4.model.service;

import com.ss4.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService extends IService<Category>{
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Category> findByNameContainingIgnoreCase(String name);

    Category findByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
