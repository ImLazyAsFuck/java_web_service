package com.ss16.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private String period;
    private long totalTicketSold;
    private long totalComboUsed;
    private long totalCustomer;
    private double totalRevenue;
}
