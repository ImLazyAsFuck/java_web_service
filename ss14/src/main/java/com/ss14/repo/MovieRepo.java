package com.ss14.repo;

import com.ss14.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long>{
    boolean existsByTitle(String title);
}
