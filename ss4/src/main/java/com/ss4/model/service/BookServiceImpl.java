package com.ss4.model.service;

import com.ss4.model.entity.Book;
import com.ss4.model.repo.BookRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Override
    public Page<Book> findBooksByNameContainingIgnoreCase(String name, Pageable pageable) {
        validateName(name);
        return bookRepo.findBooksByNameContainingIgnoreCase(name.trim(), pageable);
    }

    @Override
    public List<Book> findBooksByNameContainingIgnoreCase(String name) {
        validateName(name);
        return bookRepo.findBooksByNameContainingIgnoreCase(name.trim());
    }

    @Override
    public Book findByName(String name) {
        validateName(name);
        return bookRepo.findByName(name.trim());
    }

    @Override
    public List<Book> findByNameContainingIgnoreCase(String name){
        validateName(name);
        return bookRepo.findBooksByNameContainingIgnoreCase(name.trim());
    }

    @Override
    public Page<Book> findByNameContainingIgnoreCase(String name, Pageable pageable){
        validateName(name);
        return bookRepo.findBooksByNameContainingIgnoreCase(name.trim(), pageable);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return name != null && id != null && bookRepo.existsByNameAndIdNot(name.trim(), id);
    }

    @Override
    public List<Book> findAllByOrderByIdAsc(){
        return bookRepo.findAllByOrderByIdAsc();
    }

    @Override
    public List<Book> findAllByOrderByIdDesc(){
        return bookRepo.findAllByOrderByIdDesc();
    }

    @Override
    public Page<Book> findByNameContainingOrderByIdAsc(String name, Pageable pageable){
        validateName(name);
        return bookRepo.findBooksByNameContainingOrderByIdAsc(name.trim(), pageable);
    }

    @Override
    public Page<Book> findByNameContainingOrderByIdDesc(String name, Pageable pageable){
        validateName(name);
        return bookRepo.findBooksByNameContainingOrderByIdDesc(name.trim(), pageable);
    }

    @Override
    public Book findById(Long id) {
        if (id == null) throw new IllegalArgumentException("ID không được null");
        return bookRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sách với ID: " + id));
    }

    @Override
    public void save(Book book) {
        validateBook(book);
        book.setName(book.getName().trim());
        if (bookRepo.findByName(book.getName()) != null) {
            throw new IllegalArgumentException("Tên sách đã tồn tại");
        }
        bookRepo.save(book);
    }

    @Override
    public void update(Book book) {
        validateBook(book);
        if (book.getId() == null || !bookRepo.existsById(book.getId())) {
            throw new IllegalArgumentException("Không thể cập nhật: sách không tồn tại");
        }
        if (bookRepo.existsByNameAndIdNot(book.getName(), book.getId())) {
            throw new IllegalArgumentException("Tên sách đã tồn tại ở bản ghi khác");
        }
        bookRepo.save(book);
    }

    @Override
    public void delete(Book book) {
        if (book == null || book.getId() == null) {
            throw new IllegalArgumentException("Thông tin sách không hợp lệ");
        }
        if (!bookRepo.existsById(book.getId())) {
            throw new IllegalArgumentException("Sách không tồn tại");
        }
        bookRepo.delete(book);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) throw new IllegalArgumentException("ID không hợp lệ");
        if (!bookRepo.existsById(id)) {
            throw new IllegalArgumentException("Sách không tồn tại");
        }
        bookRepo.deleteById(id);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return id != null && bookRepo.existsById(id);
    }

    private void validateBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Sách không được null");
        validateName(book.getName());
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Tác giả không được để trống");
        }
        if (book.getPublisher() == null || book.getPublisher().trim().isEmpty()) {
            throw new IllegalArgumentException("Nhà xuất bản không được để trống");
        }
        if (book.getYear() <= 0) {
            throw new IllegalArgumentException("Năm xuất bản phải > 0");
        }
        if (book.getPrice() <= 0) {
            throw new IllegalArgumentException("Giá sách phải > 0");
        }
        if (book.getCategory() == null || book.getCategory().getId() == null) {
            throw new IllegalArgumentException("Thể loại không được để trống");
        }
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Tên sách không được null");
        }
    }

}

