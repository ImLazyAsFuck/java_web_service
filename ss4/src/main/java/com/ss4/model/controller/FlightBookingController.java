package com.ss4.model.controller;

import com.ss4.model.dto.BookingDTO;
import com.ss4.model.entity.Flight;
import com.ss4.model.service.BookingService;
import com.ss4.model.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class FlightBookingController {
    private final FlightService flightService;
    private final BookingService bookingService;

    @GetMapping("/flights")
    public String listFlights(@RequestParam(defaultValue = "") String departure,
                              @RequestParam(defaultValue = "") String destination,
                              @RequestParam(defaultValue = "0") int page,
                              Model model) {
        Page<Flight> flights = flightService.search(departure, destination, PageRequest.of(page, 5));
        model.addAttribute("flights", flights);
        return "flight/list";
    }

    @GetMapping("/book")
    public String showBookingForm(@RequestParam("flightId") Long flightId, Model model) {
        model.addAttribute("flightId", flightId);
        model.addAttribute("bookingDTO", new BookingDTO());
        return "booking/create";
    }

    @PostMapping("/book")
    public String submitBooking(@ModelAttribute BookingDTO dto) {
        bookingService.book(dto);
        return "redirect:/my-bookings?phone=" + dto.getCustomerPhone();
    }

    @GetMapping("/my-bookings")
    public String viewBookings(@RequestParam("phone") String phone, Model model) {
        model.addAttribute("bookings", bookingService.findByCustomerPhone(phone));
        return "booking/list";
    }

    @GetMapping("/cancel-booking/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancel(id);
        return "redirect:/flights";
    }

    @GetMapping("/flights/create")
    public String showCreateForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flight/create";
    }

    @PostMapping("/flights/create")
    public String createFlight(@ModelAttribute Flight flight) {
        flightService.save(flight);
        return "redirect:/flights";
    }

}
