package com.ss2.repo;

import com.ss2.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findBySeatNumber(String seatNumber);
    List<Seat> findByScreenRoomId(Long screenRoomId);
}
