package com.example.RetailRewardsCalculator.repository;

import com.example.RetailRewardsCalculator.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {

    //public List<Purchase>  findAllByCustomerIdAndPurchaseDateAfterAndSpendValueGreaterThan(Integer customerId, Date startDate, double minSpendValue);

    @Query(value = "Select * from Purchase WHERE customer_id=?1 AND purchase_date > ?2  AND spend_value > ?3 ", nativeQuery = true)
    public List<Purchase>  findAllPurchases(Integer customerId, Date startDate, double minSpendValue);


}
