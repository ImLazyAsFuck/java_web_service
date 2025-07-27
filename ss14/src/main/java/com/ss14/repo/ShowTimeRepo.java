package com.ss14.repo;

import com.ss14.model.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimeRepo extends JpaRepository<ShowTime, Long>{
    boolean existsByMovieId(Long movieId);


}
