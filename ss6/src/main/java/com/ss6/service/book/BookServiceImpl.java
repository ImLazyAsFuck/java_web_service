package com.ss6.service.book;

import com.ss6.model.entity.Book;
import com.ss6.repo.BookRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepo bookRepo;

    @Override
    public void deleteById(Long id){
        bookRepo.deleteById(id);
    }

    @Override
    public List<Book> findAll(){
        return bookRepo.findAll();
    }

    @Override
    public Book findById(Long id){
        return bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
    }

    @Override
    public Book save(Book book){
        return bookRepo.save(book);
    }

    @Override
    public Book update(Book book){
        Book existingBook =findById(book.getId());
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        return bookRepo.save(existingBook);
    }
}
