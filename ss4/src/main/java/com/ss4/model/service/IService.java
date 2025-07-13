package com.ss4.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T>{
    T findById(Long id);
    void save(T t);
    void delete(T t);
    void deleteById(Long id);
    void update(T t);
    Iterable<T> findAll();
    boolean existsById(Long id);
}
