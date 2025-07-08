package com.ss2.service;

import com.ss2.entity.Movie;
import com.ss2.repo.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService implements IService<Movie, Long> {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    @Override
    public void save(Movie movie) {
        validate(movie);
        movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        validateId(id);
        return movieRepository.findById(id);
    }

    @Override
    public void update(Long id, Movie updated) {
        validateId(id);
        validate(updated);

        Movie existing = movieRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phim id " + id));

        existing.setTitle(updated.getTitle());
        existing.setGenre(updated.getGenre());
        existing.setDuration(updated.getDuration());

        movieRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        validateId(id);
        if (!movieRepository.existsById(id)) {
            throw new EntityNotFoundException("Phim không tồn tại id " + id);
        }
        movieRepository.deleteById(id);
    }

    private void validate(Movie movie) {
        if (movie == null)
            throw new IllegalArgumentException("Phim không được null");
        if (!StringUtils.hasText(movie.getTitle()))
            throw new IllegalArgumentException("Tên phim không được để trống");
        if (!StringUtils.hasText(movie.getGenre()))
            throw new IllegalArgumentException("Thể loại không được để trống");
        if (movie.getDuration() == null || movie.getDuration() <= 0)
            throw new IllegalArgumentException("Thời lượng phải lớn hơn 0");
    }

    private void validateId(Long id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("ID không hợp lệ");
    }
}
