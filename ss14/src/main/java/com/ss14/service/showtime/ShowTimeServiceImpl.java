package com.ss14.service.showtime;

import com.ss14.model.dto.request.ShowTimeRequestDTO;
import com.ss14.model.entity.Movie;
import com.ss14.model.entity.ShowTime;
import com.ss14.repo.MovieRepo;
import com.ss14.repo.ShowTimeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService{
    private final ShowTimeRepo showTimeRepo;
    private final MovieRepo movieRepo;

    @Override
    public List<ShowTime> getAllShowTimes(){
        return showTimeRepo.findAll();
    }

    @Override
    public ShowTime saveShowTime(ShowTimeRequestDTO showTimeRequestDTO){
        Movie movie = movieRepo.findById(showTimeRequestDTO.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("movie with id " + showTimeRequestDTO.getMovieId() + " not found"));
        if(showTimeRepo.existsByMovieId(showTimeRequestDTO.getMovieId())){
            throw new IllegalArgumentException("Movie is already shown");
        }
        return showTimeRepo.save(ShowTime.builder()
                .movie(movie)
                .startTime(LocalDateTime.parse(showTimeRequestDTO.getStartTime()))
                .room(showTimeRequestDTO.getRoom())
                .build());
    }
}
