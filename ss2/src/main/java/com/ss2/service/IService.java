package com.ss2.service;

import java.util.List;
import java.util.Optional;

public interface IService<T, ID> {
    List<T> findAll();
    void save(T entity);
    Optional<T> findById(ID id);
    void update(ID id, T updatedEntity);
    void delete(ID id);
}
