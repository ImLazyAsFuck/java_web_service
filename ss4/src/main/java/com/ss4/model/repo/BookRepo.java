package com.ss4.model.repo;

import com.ss4.model.entity.Book;
import com.ss4.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepo extends JpaRepository<Book, Long>{
    Book findByName(String name);
    List<Book> findBooksByNameContainingIgnoreCase(String name);

    @Query("select case when count(b) > 0 then true else false end from Book b where b.name = :name and b.id <> :id")
    boolean existsByNameAndIdNot(String name, Long id);

    Page<Book> findBooksByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Book> findAllByOrderByIdAsc();
    List<Book> findAllByOrderByIdDesc();

    @Query("SELECT b FROM Book b WHERE b.name LIKE %:name% ORDER BY b.id ASC")
    Page<Book> findBooksByNameContainingOrderByIdAsc(String name, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.name LIKE %:name% ORDER BY b.id DESC")
    Page<Book> findBooksByNameContainingOrderByIdDesc(String name, Pageable pageable);
}
