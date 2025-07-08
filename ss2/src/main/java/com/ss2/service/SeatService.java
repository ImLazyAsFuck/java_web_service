package com.ss2.service;

import com.ss2.entity.Seat;
import com.ss2.repo.ScreenRoomRepository;
import com.ss2.repo.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatService implements IService<Seat, Long> {

    private final SeatRepository seatRepository;
    private final ScreenRoomRepository screenRoomRepository;

    @Override
    public List<Seat> findAll(){
        return seatRepository.findAll();
    }

    @Override
    public void save(Seat seat) {
        validate(seat);
        seatRepository.save(seat);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        validateId(id);
        return seatRepository.findById(id);
    }

    @Override
    public void update(Long id, Seat updated) {
        validateId(id);
        validate(updated);

        Seat existing = seatRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy ghế id " + id));

        existing.setSeatNumber(updated.getSeatNumber());
        existing.setScreenRoom(updated.getScreenRoom());

        seatRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        validateId(id);
        if (!seatRepository.existsById(id))
            throw new EntityNotFoundException("Ghế không tồn tại id " + id);
        seatRepository.deleteById(id);
    }

    private void validate(Seat seat) {
        if (seat == null)
            throw new IllegalArgumentException("Ghế không được null");
        if (!StringUtils.hasText(seat.getSeatNumber()))
            throw new IllegalArgumentException("Số ghế không được để trống");
        if (seat.getScreenRoom() == null || seat.getScreenRoom().getId() == null ||
            !screenRoomRepository.existsById(seat.getScreenRoom().getId()))
            throw new IllegalArgumentException("Phòng chiếu không hợp lệ hoặc không tồn tại");
    }

    private void validateId(Long id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("ID không hợp lệ");
    }
}
