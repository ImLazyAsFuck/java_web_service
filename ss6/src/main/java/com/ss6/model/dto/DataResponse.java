package com.ss6.model.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse<T>{
    private T data;
    private HttpStatus status;
}
