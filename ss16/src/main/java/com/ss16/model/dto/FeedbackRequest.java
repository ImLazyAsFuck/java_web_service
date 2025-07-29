package com.ss16.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;


@Data
public class FeedbackRequest {
    private Long playAreaId;
    private Long comboId;
    @Min(1)
    @Max(5)
    private Integer rating;
    private String comment;
}
