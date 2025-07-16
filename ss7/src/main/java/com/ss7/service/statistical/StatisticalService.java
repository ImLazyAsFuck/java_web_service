// Interface
package com.ss7.service.statistical;

import java.util.Map;

public interface StatisticalService {
    int countRemainingSeeds();
    double totalHarvestMoneyThisMonth();
    double totalPaymentSlipsThisMonth();
    Map<String, Double> profitLossOverYear();
    double totalWorkerSalariesThisMonth();
}
