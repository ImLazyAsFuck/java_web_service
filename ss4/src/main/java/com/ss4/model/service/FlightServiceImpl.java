package com.ss4.model.service;

import com.ss4.model.entity.Flight;
import com.ss4.model.repo.FlightRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightServiceImpl implements FlightService {
    private final FlightRepo flightRepo;

    @Override
    public Page<Flight> search(String departure, String destination, Pageable pageable) {
        return flightRepo.findByDepartureContainingIgnoreCaseAndDestinationContainingIgnoreCase(departure, destination, pageable);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepo.findAll();
    }

    @Override
    public void save(Flight flight){
        flightRepo.save(flight);
    }

}
