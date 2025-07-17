package com.ss8.model.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderDetailDTO {
    private Long dishId;
    private Integer quantity;
    private Double priceBuy;
}
