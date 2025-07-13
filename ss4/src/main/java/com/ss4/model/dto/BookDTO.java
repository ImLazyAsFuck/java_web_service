package com.ss4.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;

    @NotBlank(message = "Tên sách không được để trống")
    private String name;

    @NotBlank(message = "Tên tác giả không được để trống")
    private String author;

    @NotBlank(message = "Tên nhà xuất bản không được để trống")
    private String publisher;

    @NotNull(message = "Thể loại không được để trống")
    private Long categoryId;

    private String categoryName;

    @NotNull(message = "Năm xuất bản không được để trống")
    @Min(value = 1000, message = "Năm xuất bản không hợp lệ")
    private Integer year;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 0, message = "Giá không được âm")
    private Double price;
}
