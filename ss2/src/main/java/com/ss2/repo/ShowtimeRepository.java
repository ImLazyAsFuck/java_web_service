package com.ss2.repo;

import com.ss2.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long>{
    List<Showtime> findByMovieId(Long movieId);
    List<Showtime> findByScreenRoomId(Long screenRoomId);
}
