package com.ss6.controller;

import com.ss6.model.dto.DataResponse;
import com.ss6.model.entity.Book;
import com.ss6.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController{

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Book>>> getAllBooks(){
        return ResponseEntity.ok(new DataResponse<>(bookService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Book>> saveBook(@RequestBody Book book){
        return ResponseEntity.ok(new DataResponse<>(bookService.save(book), HttpStatus.CREATED));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataResponse<Book>> updateBook(@RequestBody Book book){
        return ResponseEntity.ok(new DataResponse<>(bookService.update(book), HttpStatus.OK));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<Book>> getBookById(Long id){
        return ResponseEntity.ok(new DataResponse<>(bookService.findById(id), HttpStatus.OK));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataResponse<Void>> deleteBookById(@PathVariable Long id){
        bookService.deleteById(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.NO_CONTENT));
    }
}
