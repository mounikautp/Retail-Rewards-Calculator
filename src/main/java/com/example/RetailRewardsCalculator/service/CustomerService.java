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

    public Rewards calculateRewards(Integer customerId) throws Exception {

        Date searchStartDate = getDateOlderThan(LocalDate.now(), 90, ChronoUnit.DAYS);
        List<Purchase> purchaseList = purchaseService.retrievePurchases(customerId, searchStartDate, MIN_TRANS_VALUE);
        if(purchaseList.isEmpty()){
            throw new Exception("No Purchases for the customer:"+ customerId + " with the valid purchases");
        }
        int rewardPoints = purchaseList.stream().mapToInt(purchase -> calculateRewardPoints(purchase)).sum();
        return buildRewards(customerId, purchaseList, rewardPoints);
    }

    private int calculateRewardPoints(Purchase purchase) {
        if (purchase == null) return 0;

        double spendValue = purchase.getSpendValue();

        if (spendValue < MIN_TRANS_VALUE) return 0;

        int rewardPoints = 0;

        if (spendValue > 100) {
            rewardPoints = (int) (spendValue - 100) * 2;
            spendValue = spendValue - rewardPoints / 2;
        }

        if (spendValue > 50) {
            rewardPoints = rewardPoints + (int) (spendValue-50);
        }

        return rewardPoints;

    }

    private Date getDateOlderThan(LocalDate startDate, int value, ChronoUnit unit) {
        LocalDate searchDate = startDate.minus(value, unit);
        return Date.from(searchDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Rewards buildRewards(int customerId, List<Purchase> purchaseList, int rewardPoints) {
        return Rewards.builder().customerId(customerId).purchaseList(purchaseList).rewardPoints(rewardPoints).build();
    }
}
