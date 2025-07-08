package com.ss2.controller;

import com.ss2.entity.Showtime;
import com.ss2.service.MovieService;
import com.ss2.service.ScreenRoomService;
import com.ss2.service.ShowtimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    private final MovieService movieService;
    private final ScreenRoomService screenRoomService;

    @GetMapping
    public String listShowtimes(
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false) Long screenRoomId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            Model model) {

        List<Showtime> showtimes = showtimeService.findAll();

        if (movieId != null || screenRoomId != null || startDate != null) {
            showtimes = showtimes.stream()
                    .filter(st -> movieId == null || st.getMovie().getId().equals(movieId))
                    .filter(st -> screenRoomId == null || st.getScreenRoom().getId().equals(screenRoomId))
                    .filter(st -> startDate == null || st.getStartTime().toLocalDate().equals(startDate))
                    .toList();
        }

        model.addAttribute("showtimes", showtimes);
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        model.addAttribute("selectedMovieId", movieId);
        model.addAttribute("selectedRoomId", screenRoomId);
        model.addAttribute("selectedDate", startDate);

        return "showtime-list";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "showtime-form-add";
    }

    @PostMapping("/add")
    public String addShowtime(@Valid @ModelAttribute("showtime") Showtime showtime,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("screenRooms", screenRoomService.findAll());
            return "showtime-form-add";
        }

        try {
            showtimeService.save(showtime);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("screenRooms", screenRoomService.findAll());
            return "showtime-form-add";
        }

        return "redirect:/showtimes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Showtime showtime = showtimeService.findById(id)
                .orElse(null);

        if (showtime == null) {
            return "redirect:/showtimes?error=notfound";
        }

        model.addAttribute("showtime", showtime);
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "showtime-form-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateShowtime(@PathVariable Long id,
                                 @Valid @ModelAttribute("showtime") Showtime showtime,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("screenRooms", screenRoomService.findAll());
            return "showtime-form-edit";
        }

        try {
            showtimeService.update(id, showtime);
        } catch (IllegalArgumentException | jakarta.persistence.EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("screenRooms", screenRoomService.findAll());
            return "showtime-form-edit";
        }

        return "redirect:/showtimes";
    }

    @PostMapping("/delete/{id}")
    public String deleteShowtime(@PathVariable Long id) {
        try {
            showtimeService.delete(id);
        } catch (IllegalArgumentException | jakarta.persistence.EntityNotFoundException e) {
            return "redirect:/showtimes?error=delete";
        }

        return "redirect:/showtimes";
    }
}
