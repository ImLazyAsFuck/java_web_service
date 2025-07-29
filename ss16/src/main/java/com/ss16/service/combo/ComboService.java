package com.ss16.service.combo;

import com.ss16.model.entity.Combo;

import java.util.List;

public interface ComboService {
    List<Combo> findAll();
    Combo create(Combo combo);
    Combo update(Long id, Combo combo);
    void delete(Long id);
}
