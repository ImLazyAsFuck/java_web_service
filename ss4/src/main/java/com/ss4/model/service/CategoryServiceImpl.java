package com.ss4.model.service;

import com.ss4.model.entity.Category;
import com.ss4.model.repo.CategoryRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống");
        }
        return categoryRepo.findByNameContainingIgnoreCase(name.trim(), pageable);
    }

    @Override
    public List<Category> findByNameContainingIgnoreCase(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống");
        }
        return categoryRepo.findByNameContainingIgnoreCase(name.trim());
    }

    @Override
    public Category findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống");
        }
        return categoryRepo.findByName(name.trim());
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        if (name == null || name.trim().isEmpty()) return false;
        if (id == null) return false;
        return categoryRepo.existsByNameAndIdNot(name.trim(), id);
    }

    @Override
    public Category findById(Long id) {
        if (id == null) throw new IllegalArgumentException("ID không được null");
        return categoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy category với id: " + id));
    }

    @Override
    public void save(Category category) {
        validateCategory(category);
        category.setName(category.getName().trim());
        Category existing = categoryRepo.findByName(category.getName());
        if (existing != null) {
            throw new IllegalArgumentException("Tên category đã tồn tại");
        }
        categoryRepo.save(category);
    }

    @Override
    public void delete(Category category) {
        if (category == null || category.getId() == null) {
            throw new IllegalArgumentException("Category hoặc ID không hợp lệ");
        }
        if (!categoryRepo.existsById(category.getId())) {
            throw new IllegalArgumentException("Không tồn tại category để xóa");
        }
        categoryRepo.delete(category);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) throw new IllegalArgumentException("ID không được null");
        if (!categoryRepo.existsById(id)) {
            throw new IllegalArgumentException("Không tồn tại category với ID: " + id);
        }
        categoryRepo.deleteById(id);
    }

    @Override
    public void update(Category category) {
        validateCategory(category);
        if (category.getId() == null || !categoryRepo.existsById(category.getId())) {
            throw new IllegalArgumentException("Không thể cập nhật: ID không tồn tại");
        }
        if (categoryRepo.existsByNameAndIdNot(category.getName(), category.getId())) {
            throw new IllegalArgumentException("Tên đã tồn tại ở một category khác");
        }
        categoryRepo.save(category);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return id != null && categoryRepo.existsById(id);
    }

    private void validateCategory(Category category) {
        if (category == null) throw new IllegalArgumentException("Category không được null");
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên category không được để trống");
        }
        if (category.getName().length() > 100) {
            throw new IllegalArgumentException("Tên category không được dài quá 100 ký tự");
        }
    }
}
