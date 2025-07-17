package com.ss8.service.dish;

import com.ss8.exception.custom.ImageUploadException;
import com.ss8.exception.custom.NoResourceFoundException;
import com.ss8.model.dto.DishDTO;
import com.ss8.model.entity.Dish;
import com.ss8.repo.DishRepo;
import com.ss8.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepo dishRepo;
    private final CloudinaryService cloudinaryService;

    @Override
    public Dish findByName(String name) {
        return dishRepo.findByName(name)
                .orElseThrow(() -> new NoResourceFoundException("Không tìm thấy món ăn tên: " + name));
    }

    @Override
    public Dish createDish(DishDTO dto) {
        try {
            String imageUrl = cloudinaryService.uploadFile(dto.getImage());
            Dish dish = new Dish(null, dto.getName(), dto.getDescription(), dto.getPrice(), imageUrl);
            return dishRepo.save(dish);
        } catch (IOException e) {
            throw new ImageUploadException("Không thể tải ảnh lên Cloudinary", e);
        }
    }

    @Override
    public Dish updateDish(Long id, DishDTO dto) {
        Dish dish = dishRepo.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Không tìm thấy món ăn với ID: " + id));

        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadFile(dto.getImage());
                dish.setImage(imageUrl);
            } catch (IOException e) {
                throw new ImageUploadException("Không thể cập nhật ảnh", e);
            }
        }

        return dishRepo.save(dish);
    }

    @Override
    public void deleteDish(Long id) {
        Dish dish = dishRepo.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Không tìm thấy món ăn với ID: " + id));
        dishRepo.delete(dish);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepo.findAll();
    }
}
