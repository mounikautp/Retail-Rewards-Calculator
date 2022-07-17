package com.example.RetailRewardsCalculator.repository;

import com.example.RetailRewardsCalculator.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {

}
