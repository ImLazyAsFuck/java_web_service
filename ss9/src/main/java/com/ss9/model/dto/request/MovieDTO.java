package com.ss9.model.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class MovieDTO {
    private String title;
    private String description;
    private LocalDate releaseDate;
    private MultipartFile poster;
}
