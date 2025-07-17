package com.ss8.service.statistical;

import java.util.List;
import java.util.Map;

public interface StatisticalService {
    List<Map<String, Object>> topDishes();
    List<Map<String, Object>> topCustomers();
    double currentMonthExpenses();
    Map<String, Double> monthlyExpenses();
    Map<String, Double> monthlyRevenue();
    Map<String, Object> topEmployeeThisMonth();
}
