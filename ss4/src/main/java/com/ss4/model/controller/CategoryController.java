package com.ss4.model.controller;

import com.ss4.model.dto.CategoryDTO;
import com.ss4.model.entity.Category;
import com.ss4.model.mapper.CategoryMapper;
import com.ss4.model.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String list(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Category> categoryList;
        if (search != null && !search.trim().isEmpty()) {
            categoryList = categoryService.findByNameContainingIgnoreCase(search.trim());
            model.addAttribute("search", search);
        } else {
            categoryList = (List<Category>) categoryService.findAll();
        }

        List<CategoryDTO> categoryDTOList = categoryList.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("categories", categoryDTOList);
        return "category/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "category/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("category") @Valid CategoryDTO dto,
                         BindingResult result,
                         RedirectAttributes redirect) {
        try {
            Category existing = categoryService.findByName(dto.getName());
            if (existing != null) {
                result.rejectValue("name", "error.category", "Tên thể loại đã tồn tại");
            }
        } catch (IllegalArgumentException e) {
        }

        if (result.hasErrors()) return "category/create";

        categoryService.save(CategoryMapper.toEntity(dto));
        redirect.addFlashAttribute("success", "Thêm thể loại thành công");
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        try {
            Category category = categoryService.findById(id);
            model.addAttribute("category", CategoryMapper.toDTO(category));
            return "category/edit";
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/categories";
        }
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("category") @Valid CategoryDTO dto,
                         BindingResult result,
                         RedirectAttributes redirect) {
        if (categoryService.existsByNameAndIdNot(dto.getName(), dto.getId())) {
            result.rejectValue("name", "error.category", "Tên thể loại đã tồn tại");
        }

        if (result.hasErrors()) return "category/edit";

        try {
            categoryService.update(CategoryMapper.toEntity(dto));
            redirect.addFlashAttribute("success", "Cập nhật thể loại thành công");
        } catch (IllegalArgumentException e) {
            result.rejectValue("name", "error.category", e.getMessage());
            return "category/edit";
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            categoryService.deleteById(id);
            redirect.addFlashAttribute("success", "Xoá thể loại thành công");
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/categories";
    }
}
