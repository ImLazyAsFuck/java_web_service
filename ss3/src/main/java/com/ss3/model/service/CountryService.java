package com.ss3.model.service;

import com.ss3.model.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    CountryDTO save(CountryDTO dto);
    CountryDTO findById(Long id);
    List<CountryDTO> findAll();
    void deleteById(Long id);
    List<CountryDTO> findByNameContainingIgnoreCase(String keyword);
}