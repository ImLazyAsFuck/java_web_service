package com.ss16.service.playarea;

import com.ss16.model.entity.PlayArea;
import com.ss16.repository.PlayAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayAreaServiceImpl implements PlayAreaService {
    private final PlayAreaRepository repository;

    @Override
    public List<PlayArea> findAll() {
        return repository.findAll();
    }

    @Override
    public PlayArea create(PlayArea playArea) {
        return repository.save(playArea);
    }

    @Override
    public PlayArea update(Long id, PlayArea playArea) {
        PlayArea existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khu vui chơi"));
        existing.setName(playArea.getName());
        existing.setDescription(playArea.getDescription());
        existing.setMaxCapacity(playArea.getMaxCapacity());
        existing.setStatus(playArea.getStatus());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Không tìm thấy khu vui chơi");
        repository.deleteById(id);
    }
}
