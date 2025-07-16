package com.ss7.service;

import java.util.List;

public interface IService<T>{
    List<T> findAll();
    T findById(Long id);
    T save(T t);
    void deleteById(Long id);
    T update(T t);
}
