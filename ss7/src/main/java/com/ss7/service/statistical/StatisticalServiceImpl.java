// Implementation
package com.ss7.service.statistical;

import com.ss7.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {

    private final SeedRepo seedRepo;
    private final HarvestRepo harvestRepo;
    private final PaymentSlipRepo paymentSlipRepo;
    private final WorkerRepo workerRepo;

    @Override
    public int countRemainingSeeds() {
        return seedRepo.sumAllQuantities();
    }

    @Override
    public double totalHarvestMoneyThisMonth() {
        YearMonth thisMonth = YearMonth.now();
        return harvestRepo.sumTotalMoneyByMonth(thisMonth.getYear(), thisMonth.getMonthValue());
    }

    @Override
    public double totalPaymentSlipsThisMonth() {
        YearMonth thisMonth = YearMonth.now();
        return paymentSlipRepo.sumAmountByMonth(thisMonth.getYear(), thisMonth.getMonthValue());
    }

    @Override
    public Map<String, Double> profitLossOverYear() {
        Map<String, Double> result = new HashMap<>();
        int year = LocalDate.now().getYear();
        for (int month = 1; month <= 12; month++) {
            double income = harvestRepo.sumTotalMoneyByMonth(year, month);
            double expense = paymentSlipRepo.sumAmountByMonth(year, month);
            result.put(String.format("%02d-%d", month, year), income - expense);
        }
        return result;
    }

    @Override
    public double totalWorkerSalariesThisMonth() {
        return workerRepo.sumAllSalaries();
    }
}
