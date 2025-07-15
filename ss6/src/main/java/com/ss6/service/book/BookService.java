package com.ss6.service.book;

import com.ss6.model.entity.Book;

import java.util.List;

public interface BookService{
    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
    Book update(Book book);
    void deleteById(Long id);
}
