package com.ss9.service;

import com.ss9.model.dto.request.MovieDTO;
import com.ss9.model.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {
    Movie createMovie(MovieDTO movieDTO);
    Movie updateMovie(Long id, MovieDTO movieDTO);
    void deleteMovie(Long id);
    String uploadPoster(MultipartFile file);
    List<Movie> getAllMovies(String searchMovie);
    List<Movie> suggestMoviesByKeywords(List<String> keywords);
}
