package com.ss8.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T>{
    private T data;
    private String message;
    private boolean status;
}
