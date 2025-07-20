package com.ss9.service;

import com.ss9.model.dto.request.MovieDTO;
import com.ss9.model.entity.Movie;
import com.ss9.repo.MovieRepo;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.time.LocalDateTime;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final CloudinaryService cloudinaryService;

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";


    @Override
    public Movie createMovie(MovieDTO movieDTO) {
        try {
            String imageUrl = uploadPoster(movieDTO.getPoster());

            Movie movie = new Movie();
            movie.setTitle(movieDTO.getTitle());
            movie.setDescription(movieDTO.getDescription());
            movie.setReleaseDate(movieDTO.getReleaseDate());
            movie.setPoster(imageUrl);

            Movie saved = movieRepo.save(movie);
            logger.info("Đã thêm phim: '{}' lúc {}", saved.getTitle(), LocalDateTime.now());
            return saved;
        } catch (Exception e) {
            logger.error(RED + "Lỗi khi thêm phim: " + e.getMessage() + RESET, e);
            throw e;
        }
    }

    @Override
    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        try {
            Movie existing = movieRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Phim không tồn tại"));

            String oldInfo = existing.toString();

            if (movieDTO.getPoster() != null && !movieDTO.getPoster().isEmpty()) {
                String imageUrl = uploadPoster(movieDTO.getPoster());
                existing.setPoster(imageUrl);
            }

            existing.setTitle(movieDTO.getTitle());
            existing.setDescription(movieDTO.getDescription());
            existing.setReleaseDate(movieDTO.getReleaseDate());

            Movie updated = movieRepo.save(existing);

            logger.info(
                YELLOW + "Thông tin cũ: " + oldInfo + RESET + "\n" +
                GREEN + "Thông tin mới: " + updated + RESET
            );

            return updated;
        } catch (Exception e) {
            logger.error(RED + "Lỗi khi cập nhật phim: " + e.getMessage() + RESET, e);
            throw e;
        }
    }

    @Override
    public void deleteMovie(Long id) {
        try {
            Movie movie = movieRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Phim không tồn tại"));

            movieRepo.delete(movie);

            logger.info(
                RED + "Xóa thành công. " + RESET +
                GREEN + "Thông tin phim đã bị xóa: " + movie + RESET
            );
        } catch (Exception e) {
            logger.error(RED + "Lỗi khi xóa phim: " + e.getMessage() + RESET, e);
            throw e;
        }
    }

    @Override
    public String uploadPoster(MultipartFile file) {
        return cloudinaryService.uploadImage(file);
    }

    @Override
    public List<Movie> getAllMovies(String searchMovie) {
        long start = System.currentTimeMillis();

        List<Movie> result;

        if (searchMovie != null && !searchMovie.isBlank()) {
            result = movieRepo.findByTitleContainingIgnoreCase(searchMovie);
            logger.info(GREEN + "Tìm kiếm với từ khóa: '" + searchMovie + "'" + RESET);
        } else {
            result = movieRepo.findAll();
        }

        long duration = System.currentTimeMillis() - start;
        logger.info(GREEN + "Số lượng phim trả về: " + result.size() + RESET);
        logger.info(GREEN + "Thời gian thực hiện truy vấn: " + duration + "ms" + RESET);

        return result;
    }

    @Override
    public List<Movie> suggestMoviesByKeywords(List<String> keywords) {
        Set<Movie> suggested = new HashSet<>();
        for (String keyword : keywords) {
            List<Movie> partial = movieRepo.findByTitleContainingIgnoreCase(keyword);
            suggested.addAll(partial);
        }
        return new ArrayList<>(suggested);
    }

}
