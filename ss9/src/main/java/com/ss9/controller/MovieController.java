package com.ss9.controller;

import com.ss9.model.dto.request.MovieDTO;
import com.ss9.model.dto.response.DataResponse;
import com.ss9.model.entity.Movie;
import com.ss9.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<DataResponse<Movie>> createMovie(@ModelAttribute MovieDTO movieDTO) {
        Movie movie = movieService.createMovie(movieDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new DataResponse<>(movie, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Movie>> updateMovie(@PathVariable Long id, @ModelAttribute MovieDTO movieDTO) {
        Movie movie = movieService.updateMovie(id, movieDTO);
        return ResponseEntity.ok(new DataResponse<>(movie, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(new DataResponse<>("Xóa phim thành công", HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<Movie>>> getAllMovies(@RequestParam(required = false) String searchMovie) {
        List<Movie> movies = movieService.getAllMovies(searchMovie);
        return ResponseEntity.ok(new DataResponse<>(movies, HttpStatus.OK));
    }
}
