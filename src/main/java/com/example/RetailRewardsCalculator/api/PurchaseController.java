package com.example.RetailRewardsCalculator.api;

import com.example.RetailRewardsCalculator.domain.Purchase;
import com.example.RetailRewardsCalculator.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Purchase> OrderAPurchase(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(this.purchaseService.savePurchase(purchase), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Purchase> retrieveAllPurchases() {
        return this.purchaseService.retrieveAll();
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> retrievePurchase(@PathVariable("purchaseId") Integer purchaseId){
        Optional<Purchase>  optionalPurchase= this.purchaseService.retrieveById(purchaseId);
        if(optionalPurchase.isPresent()){
            return new ResponseEntity<>(optionalPurchase.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
