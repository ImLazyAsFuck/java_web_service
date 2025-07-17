package com.ss8.service.ingredient;

import com.ss8.model.entity.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IngredientService{
    Ingredient create(Ingredient ingredient, MultipartFile image) throws IOException;
    Ingredient update(Long id, Ingredient updated, MultipartFile image) throws IOException;
    void delete(Long id);
    List<Ingredient> findAll();

}
