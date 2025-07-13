package com.ss4.model.service;

import com.ss4.model.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {
    Page<Flight> search(String departure, String destination, Pageable pageable);
    List<Flight> findAll();
    void save(Flight flight);
}
