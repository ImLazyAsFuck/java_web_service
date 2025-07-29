package com.ss16.service.staff;

import com.ss16.model.dto.StaffCreateDTO;
import com.ss16.model.dto.StaffDTO;
import com.ss16.model.dto.StaffUpdateDTO;
import com.ss16.model.enums.ERole;

public interface StaffService {
    StaffDTO createStaff(StaffCreateDTO dto);
    StaffDTO updateMyInfo(StaffUpdateDTO dto);
    StaffDTO getMyInfo();
    StaffDTO updateRole(Long id, ERole newRole);
}
