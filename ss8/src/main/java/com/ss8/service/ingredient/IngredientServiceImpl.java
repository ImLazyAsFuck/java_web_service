package com.ss8.service.ingredient;

import com.ss8.exception.custom.NoResourceFoundException;
import com.ss8.model.entity.Ingredient;
import com.ss8.repo.IngredientRepo;
import com.ss8.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl  implements IngredientService{

    private final IngredientRepo ingredientRepository;
    private final CloudinaryService cloudinaryService;

    public Ingredient create(Ingredient ingredient, MultipartFile image) throws IOException {
        String imageUrl = cloudinaryService.uploadFile(image);
        ingredient.setImage(imageUrl);
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Long id, Ingredient updated, MultipartFile image) throws IOException {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Ingredient not found with id: " + id));

        existing.setName(updated.getName());
        existing.setStock(updated.getStock());
        existing.setExpiry(updated.getExpiry());

        if (image != null && !image.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(image);
            existing.setImage(imageUrl);
        }

        return ingredientRepository.save(existing);
    }

    public void delete(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new NoResourceFoundException("Ingredient not found with id: " + id);
        }
        ingredientRepository.deleteById(id);
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
}
