package com.example.RetailRewardsCalculator.service;

import com.example.RetailRewardsCalculator.domain.Purchase;
import com.example.RetailRewardsCalculator.domain.Rewards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    public static final double MIN_TRANS_VALUE = 50.00;
    @Autowired
    PurchaseService purchaseService;

    public Rewards calculateRewards(Integer customerId) {
        LocalDate searchDate = LocalDate.now().minus(90, ChronoUnit.DAYS);
        Date searchStartDate = Date.from(searchDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Purchase> purchaseList = purchaseService.retrievePurchases(customerId, searchStartDate, MIN_TRANS_VALUE);
        int rewardPoints = purchaseList.stream().mapToInt(purchase-> calculateRewardPoints(purchase)).sum();
        return  Rewards.builder().customerId(customerId).purchaseList(purchaseList).rewardPoints(rewardPoints).build();
    }

    private int calculateRewardPoints(Purchase purchase) {
        if (purchase == null || purchase.getSpendValue() < MIN_TRANS_VALUE) return 0;

        double spendValue = purchase.getSpendValue();

        int rewardPoints = 0;

        if (spendValue > 100) {
            rewardPoints = (int) (spendValue - 100) * 2;
            spendValue = spendValue - rewardPoints / 2;
        }
        rewardPoints =rewardPoints +  (int)Math.min((spendValue - 50), 0) * 1;

        return rewardPoints;

    }
}
