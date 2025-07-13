package com.ss4.model.repo;

import com.ss4.model.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepo extends JpaRepository<Flight, Long>{
    Page<Flight> findByDepartureContainingIgnoreCaseAndDestinationContainingIgnoreCase(String departure, String destination, Pageable pageable);


}
