package com.ss8.service.statistical;

import com.ss8.model.entity.*;
import com.ss8.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {

    private final OrderRepo orderRepo;
    private final OrderDetailRepo orderDetailRepo;
    private final CustomerRepo customerRepo;
    private final EmployeeRepo employeeRepo;
    private final IngredientRepo ingredientRepo;
    private final DishRepo dishRepo;

    @Override
    public List<Map<String, Object>> topDishes() {
        return orderDetailRepo.findAll().stream()
                .collect(Collectors.groupingBy(
                        d -> d.getDish().getName(),
                        Collectors.summingInt(OrderDetail::getQuantity)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("dish", entry.getKey());
                    result.put("quantity", entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> topCustomers() {
        return orderRepo.findAll().stream()
                .collect(Collectors.groupingBy(
                        o -> o.getCustomer().getFullName(),
                        Collectors.summingDouble(Orders::getTotalMoney)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(10)
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("customer", entry.getKey());
                    result.put("totalMoney", entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public double currentMonthExpenses() {
        YearMonth current = YearMonth.now();
        return ingredientRepo.findAll().stream()
                .filter(i -> YearMonth.from(i.getExpiry()).equals(current))
                .mapToDouble(i -> i.getStock() * 1.0)
                .sum();
    }

    @Override
    public Map<String, Double> monthlyExpenses() {
        Map<String, Double> result = new LinkedHashMap<>();
        Year year = Year.now();
        List<Ingredient> ingredients = ingredientRepo.findAll();

        for (int m = 1; m <= 12; m++) {
            int month = m;
            double total = ingredients.stream()
                    .filter(i -> i.getExpiry().getYear() == year.getValue() && i.getExpiry().getMonthValue() == month)
                    .mapToDouble(i -> i.getStock() * 1.0)
                    .sum();
            result.put(Month.of(month).name(), total);
        }
        return result;
    }

    @Override
    public Map<String, Double> monthlyRevenue() {
        Map<String, Double> result = new LinkedHashMap<>();
        Year year = Year.now();
        List<Orders> orders = orderRepo.findAll();

        for (int m = 1; m <= 12; m++) {
            int month = m;
            double total = orders.stream()
                    .filter(o -> o.getCreatedAt().getYear() == year.getValue() && o.getCreatedAt().getMonthValue() == month)
                    .mapToDouble(Orders::getTotalMoney)
                    .sum();
            result.put(Month.of(month).name(), total);
        }
        return result;
    }

    @Override
    public Map<String, Object> topEmployeeThisMonth() {
        YearMonth currentMonth = YearMonth.now();

        return orderRepo.findAll().stream()
                .filter(o -> YearMonth.from(o.getCreatedAt()).equals(currentMonth))
                .collect(Collectors.groupingBy(
                        o -> o.getEmployee().getFullname(),
                        Collectors.summingDouble(Orders::getTotalMoney)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("employee", e.getKey());
                    result.put("revenue", e.getValue());
                    return result;
                })
                .orElseGet(() -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("message", "Không có dữ liệu");
                    return result;
                });
    }
}
