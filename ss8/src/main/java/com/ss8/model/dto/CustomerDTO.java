package com.ss8.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private String fullName;
    private String phone;
    private String email;
    private Integer numberOfPayments;
}
