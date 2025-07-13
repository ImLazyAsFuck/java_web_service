package com.ss4.model.service;

import com.ss4.model.dto.BookingDTO;
import com.ss4.model.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking book(BookingDTO dto);
    List<Booking> findByCustomerPhone(String phone);
    void cancel(Long id);
}
