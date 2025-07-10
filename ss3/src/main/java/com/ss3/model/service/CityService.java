package com.ss3.model.service;

import com.ss3.model.dto.CityDTO;

import java.util.List;

public interface CityService {
    CityDTO save(CityDTO dto);
    CityDTO findById(Long id);
    List<CityDTO> findAll();
    void deleteById(Long id);
    List<CityDTO> findByNameContainingIgnoreCase(String keyword);
}
