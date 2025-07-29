package com.ss16.service.combo;

import com.ss16.model.entity.Combo;
import com.ss16.repository.ComboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {

    private final ComboRepository comboRepository;

    @Override
    public List<Combo> findAll() {
        return comboRepository.findAll();
    }

    @Override
    public Combo create(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public Combo update(Long id, Combo combo) {
        Combo existing = comboRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy combo"));

        existing.setName(combo.getName());
        existing.setDescription(combo.getDescription());
        existing.setItems(combo.getItems());
        existing.setPrice(combo.getPrice());
        existing.setStatus(combo.getStatus());

        return comboRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!comboRepository.existsById(id)) {
            throw new RuntimeException("Combo không tồn tại");
        }
        comboRepository.deleteById(id);
    }
}
