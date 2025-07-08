package com.ss2.controller;

import com.ss2.entity.Movie;
import com.ss2.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public String getAllMovies(Model model) {
        model.addAttribute("movies", movieService.findAll());
        return "movie-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie-add";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie, RedirectAttributes redirectAttributes) {
        try {
            movieService.save(movie);
            redirectAttributes.addFlashAttribute("success", "Thêm phim thành công.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/movies-add";
        }
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Movie> movieOpt = movieService.findById(id);
        if (movieOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Phim không tồn tại.");
            return "redirect:/movies";
        }
        model.addAttribute("movie", movieOpt.get());
        return "movie-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie, RedirectAttributes redirectAttributes) {
        try {
            movieService.update(id, movie);
            redirectAttributes.addFlashAttribute("success", "Cập nhật phim thành công.");
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/movies/edit/" + id;
        }
        return "redirect:/movies";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            movieService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Xóa phim thành công.");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/movies";
    }
}
