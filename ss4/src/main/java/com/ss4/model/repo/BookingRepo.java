package com.ss4.model.repo;

import com.ss4.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long>{
    @Query("select b from Booking b where b.customerPhone = :phone")
    List<Booking> findByCustomerPhone(String phone);
}
