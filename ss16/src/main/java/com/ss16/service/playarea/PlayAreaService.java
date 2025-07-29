package com.ss16.service.playarea;

import com.ss16.model.entity.PlayArea;

import java.util.List;

public interface PlayAreaService {
    List<PlayArea> findAll();
    PlayArea create(PlayArea playArea);
    PlayArea update(Long id, PlayArea playArea);
    void delete(Long id);
}
