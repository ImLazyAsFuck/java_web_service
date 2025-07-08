package com.ss2.service;

import com.ss2.entity.Theater;
import com.ss2.repo.TheaterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterService implements IService<Theater, Long> {

    private final TheaterRepository theaterRepository;

    @Override
    public List<Theater> findAll(){
        return theaterRepository.findAll();
    }

    @Override
    public void save(Theater theater) {
        validate(theater);
        theaterRepository.save(theater);
    }

    @Override
    public Optional<Theater> findById(Long id) {
        validateId(id);
        return theaterRepository.findById(id);
    }

    @Override
    public void update(Long id, Theater updated) {
        validateId(id);
        validate(updated);

        Theater existing = theaterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy rạp với id: " + id));

        if (StringUtils.hasText(updated.getName())) {
            existing.setName(updated.getName());
        }

        if (StringUtils.hasText(updated.getAddress())) {
            existing.setAddress(updated.getAddress());
        }

        theaterRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        validateId(id);
        if (!theaterRepository.existsById(id)) {
            throw new EntityNotFoundException("Không tồn tại rạp để xóa với id: " + id);
        }
        theaterRepository.deleteById(id);
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("ID không hợp lệ");
    }

    private void validate(Theater theater) {
        if (theater == null) throw new IllegalArgumentException("Theater không được null");
        if (!StringUtils.hasText(theater.getName()))
            throw new IllegalArgumentException("Tên rạp không được để trống");
        if (!StringUtils.hasText(theater.getAddress()))
            throw new IllegalArgumentException("Địa điểm không được để trống");
    }
}
