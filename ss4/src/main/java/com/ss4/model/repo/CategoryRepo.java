package com.ss4.model.repo;

import com.ss4.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Category> findByNameContainingIgnoreCase(String name);

    Category findByName(String name);

    @Query("select case when count(c) > 0 then true else false end from Category c where c.name = :name and c.id <> :id")
    boolean existsByNameAndIdNot(String name, Long id);
}
