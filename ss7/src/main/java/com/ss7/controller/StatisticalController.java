package com.ss7.controller;

import com.ss7.service.statistical.StatisticalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticalController {

    private final StatisticalService statisticalService;

    @GetMapping("/remaining-seeds")
    public int countRemainingSeeds() {
        return statisticalService.countRemainingSeeds();
    }

    @GetMapping("/harvest-money")
    public double totalHarvestMoneyThisMonth() {
        return statisticalService.totalHarvestMoneyThisMonth();
    }

    @GetMapping("/payment-slips")
    public double totalPaymentSlipsThisMonth() {
        return statisticalService.totalPaymentSlipsThisMonth();
    }

    @GetMapping("/profit-loss")
    public Map<String, Double> profitLossOverYear() {
        return statisticalService.profitLossOverYear();
    }

    @GetMapping("/worker-salary")
    public double totalWorkerSalariesThisMonth() {
        return statisticalService.totalWorkerSalariesThisMonth();
    }
}
