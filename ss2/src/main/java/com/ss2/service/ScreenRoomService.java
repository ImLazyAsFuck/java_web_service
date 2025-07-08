package com.ss2.service;

import com.ss2.entity.ScreenRoom;
import com.ss2.repo.ScreenRoomRepository;
import com.ss2.repo.TheaterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScreenRoomService implements IService<ScreenRoom, Long> {

    private final ScreenRoomRepository screenRoomRepository;
    private final TheaterRepository theaterRepository;

    @Override
    public List<ScreenRoom> findAll(){
        return screenRoomRepository.findAll();
    }

    @Override
    public void save(ScreenRoom room) {
        validate(room);
        screenRoomRepository.save(room);
    }

    @Override
    public Optional<ScreenRoom> findById(Long id) {
        validateId(id);
        return screenRoomRepository.findById(id);
    }

    @Override
    public void update(Long id, ScreenRoom updated) {
        validateId(id);
        validate(updated);

        ScreenRoom existing = screenRoomRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phòng chiếu id " + id));

        existing.setName(updated.getName());
        existing.setCapacity(updated.getCapacity());
        existing.setTheater(updated.getTheater());

        screenRoomRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        validateId(id);
        if (!screenRoomRepository.existsById(id))
            throw new EntityNotFoundException("Phòng chiếu không tồn tại id " + id);
        screenRoomRepository.deleteById(id);
    }

    private void validate(ScreenRoom room) {
        if (room == null)
            throw new IllegalArgumentException("Phòng chiếu không được null");
        if (!StringUtils.hasText(room.getName()))
            throw new IllegalArgumentException("Tên phòng không được để trống");
        if (room.getCapacity() == null || room.getCapacity() <= 0)
            throw new IllegalArgumentException("Sức chứa phải lớn hơn 0");
        if (room.getTheater() == null || room.getTheater().getId() == null ||
            !theaterRepository.existsById(room.getTheater().getId()))
            throw new IllegalArgumentException("Rạp chiếu không hợp lệ hoặc không tồn tại");
    }

    private void validateId(Long id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("ID không hợp lệ");
    }
}
