package com.babotea.service.revenuereport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public interface RevenueReportService {
    BigDecimal getRevenueByDay(LocalDate date);
    BigDecimal getRevenueByMonth(YearMonth month);
    BigDecimal getRevenueByYear(int year);
}
