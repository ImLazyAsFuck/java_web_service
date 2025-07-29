package com.ss16.model.dto;

import lombok.Builder;
import lombok.Data;
import com.ss16.model.enums.ERole;
import com.ss16.model.enums.StaffStatus;

@Data
@Builder
public class StaffDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private ERole role;
    private StaffStatus status;
}
