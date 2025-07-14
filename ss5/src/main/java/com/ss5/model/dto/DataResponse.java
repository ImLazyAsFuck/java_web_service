package com.ss5.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse<T> {
    private T data;
    private int status;

    public static <T> DataResponse<T> of(T data, HttpStatus status) {
        return new DataResponse<>(data, status.value());
    }
}
