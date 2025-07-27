package com.ss14.controller;

import com.ss14.model.dto.response.APIResponse;
import com.ss14.model.entity.Movie;
import com.ss14.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController{
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Movie>>> getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(
                new APIResponse<>(true, "Get all movies successfully", movies, HttpStatus.OK)
        );
    }
}
