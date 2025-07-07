package com.ss1.controller;

import com.ss1.dto.ProductDto;
import com.ss1.entity.Product;
import com.ss1.service.ProductService;
import com.ss1.validation.customerException.FieldValidationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute @Valid ProductDto productDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "product-form";
        }

        Product product = mapDtoToEntity(productDto);
        try {
            productService.save(product);
            return "redirect:/products";
        } catch (FieldValidationException e) {
            model.addAttribute("errorField", e.getFieldName());
            model.addAttribute("errorMessage", e.getMessage());
            return "product-form";
        }
    }

    @GetMapping("/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        model.addAttribute("productDto", dto);
        model.addAttribute("productId", id);
        return "product-form";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute @Valid ProductDto productDto,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productId", id);
            return "product-form";
        }

        Product product = mapDtoToEntity(productDto);
        product.setId(id);
        try {
            productService.update(product);
            return "redirect:/products";
        } catch (FieldValidationException e) {
            model.addAttribute("errorField", e.getFieldName());
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("productId", id);
            return "product-form";
        }
    }


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    private Product mapDtoToEntity(ProductDto dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        return p;
    }
}
