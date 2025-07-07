package com.ss1.validation.customerException;

import lombok.Getter;

@Getter
public class FieldValidationException extends RuntimeException {
    private final String fieldName;

    public FieldValidationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

}
