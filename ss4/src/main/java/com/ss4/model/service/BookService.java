package com.ss4.model.service;

import com.ss4.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService extends IService<Book> {
    Book findByName(String name);

    List<Book> findByNameContainingIgnoreCase(String name);

    Page<Book> findByNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByNameAndIdNot(String name, Long id);

    List<Book> findAllByOrderByIdAsc();

    List<Book> findAllByOrderByIdDesc();

    Page<Book> findByNameContainingOrderByIdAsc(String name, Pageable pageable);

    Page<Book> findByNameContainingOrderByIdDesc(String name, Pageable pageable);

    Page<Book> findBooksByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Book> findBooksByNameContainingIgnoreCase(String name);
}

