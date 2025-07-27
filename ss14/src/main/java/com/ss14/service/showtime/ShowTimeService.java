package com.ss14.service.showtime;

import com.ss14.model.dto.request.ShowTimeRequestDTO;
import com.ss14.model.entity.ShowTime;

import java.util.List;

public interface ShowTimeService{
    List<ShowTime> getAllShowTimes();
    ShowTime saveShowTime(ShowTimeRequestDTO showTimeRequestDTO);
}
