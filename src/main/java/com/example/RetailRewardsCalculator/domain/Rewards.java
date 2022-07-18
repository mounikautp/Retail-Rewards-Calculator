package com.example.RetailRewardsCalculator.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Rewards {
    private Integer customerId;
    private Integer rewardPoints;
    List<Purchase> purchaseList;
}
