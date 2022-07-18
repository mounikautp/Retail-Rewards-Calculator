package com.example.RetailRewardsCalculator.service;

import com.example.RetailRewardsCalculator.domain.Purchase;
import com.example.RetailRewardsCalculator.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase savePurchase(Purchase purchase){
        return this.purchaseRepository.save(purchase);
    }

    public List<Purchase> retrieveAll(){
        return this.purchaseRepository.findAll();
    }

    public Optional<Purchase>  retrieveById(Integer purchaseId){
        return this.purchaseRepository.findById(purchaseId);
    }

    public List<Purchase>  retrievePurchases(Integer customerId, Date date, Double minValue){
        return this.purchaseRepository.findAllPurchases(customerId,date,minValue);
    }
}
