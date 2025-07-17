package com.ss8.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private String fullname;
    private String phone;
    private String address;
    private String position;
    private Double salary;
}
