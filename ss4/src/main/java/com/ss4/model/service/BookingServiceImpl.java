package com.ss4.model.service;

import com.ss4.model.dto.BookingDTO;
import com.ss4.model.entity.Booking;
import com.ss4.model.entity.BookingStatus;
import com.ss4.model.entity.Flight;
import com.ss4.model.repo.BookingRepo;
import com.ss4.model.repo.FlightRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final FlightRepo flightRepo;

    @Override
    public Booking book(BookingDTO dto) {
        Flight flight = flightRepo.findById(dto.getFlightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setCustomerName(dto.getCustomerName());
        booking.setCustomerPhone(dto.getCustomerPhone());
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(BookingStatus.BOOKED);

        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> findByCustomerPhone(String phone) {
        return bookingRepo.findByCustomerPhone(phone);
    }

    @Override
    public void cancel(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepo.save(booking);
    }
}
