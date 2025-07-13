package com.ss4.model.mapper;

import com.ss4.model.dto.BookDTO;
import com.ss4.model.entity.Book;
import com.ss4.model.entity.Category;
import com.ss4.model.service.CategoryService;

public class BookMapper {

    public static Book toEntity(BookDTO dto, CategoryService categoryService) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setName(dto.getName() != null ? dto.getName().trim() : null);
        book.setAuthor(dto.getAuthor() != null ? dto.getAuthor().trim() : null);
        book.setPublisher(dto.getPublisher() != null ? dto.getPublisher().trim() : null);
        book.setYear(dto.getYear());
        book.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null) {
            try {
                Category category = categoryService.findById(dto.getCategoryId());
                book.setCategory(category);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Thể loại không tồn tại: " + e.getMessage());
            }
        }

        return book;
    }

    public static BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setPublisher(book.getPublisher());
        dto.setYear(book.getYear());
        dto.setPrice(book.getPrice());
        dto.setCategoryId(book.getCategory().getId());

        return dto;
    }
}
