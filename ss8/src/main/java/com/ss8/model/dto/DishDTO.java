package com.ss8.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {

    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    private Double price;
    private MultipartFile image;
}
