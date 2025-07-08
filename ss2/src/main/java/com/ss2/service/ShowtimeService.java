package com.ss2.service;

import com.ss2.entity.Showtime;
import com.ss2.repo.MovieRepository;
import com.ss2.repo.ScreenRoomRepository;
import com.ss2.repo.ShowtimeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowtimeService implements IService<Showtime, Long> {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final ScreenRoomRepository screenRoomRepository;

    @Override
    public List<Showtime> findAll(){
        return showtimeRepository.findAll();
    }

    @Override
    public void save(Showtime showtime) {
        validate(showtime);
        showtimeRepository.save(showtime);
    }

    @Override
    public Optional<Showtime> findById(Long id) {
        validateId(id);
        return showtimeRepository.findById(id);
    }

    @Override
    public void update(Long id, Showtime updated) {
        validateId(id);
        validate(updated);

        Showtime existing = showtimeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy suất chiếu id " + id));

        existing.setMovie(updated.getMovie());
        existing.setScreenRoom(updated.getScreenRoom());
        existing.setStartTime(updated.getStartTime());
        existing.setEndTime(updated.getEndTime());
        existing.setNumberSeatEmpty(updated.getNumberSeatEmpty());

        showtimeRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        validateId(id);
        if (!showtimeRepository.existsById(id))
            throw new EntityNotFoundException("Suất chiếu không tồn tại id " + id);
        showtimeRepository.deleteById(id);
    }

    private void validate(Showtime st) {
        if (st == null)
            throw new IllegalArgumentException("Showtime không được null");

        if (st.getMovie() == null || st.getMovie().getId() == null ||
            !movieRepository.existsById(st.getMovie().getId()))
            throw new IllegalArgumentException("Phim không hợp lệ");

        if (st.getScreenRoom() == null || st.getScreenRoom().getId() == null ||
            !screenRoomRepository.existsById(st.getScreenRoom().getId()))
            throw new IllegalArgumentException("Phòng chiếu không hợp lệ");

        if (st.getStartTime() == null || st.getEndTime() == null ||
            !st.getEndTime().isAfter(st.getStartTime()))
            throw new IllegalArgumentException("Thời gian chiếu không hợp lệ");

        if (st.getNumberSeatEmpty() < 0)
            throw new IllegalArgumentException("Số ghế trống không hợp lệ");
    }

    private void validateId(Long id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("ID không hợp lệ");
    }
}
