package com.example.RetailRewardsCalculator.api;

import com.example.RetailRewardsCalculator.domain.Rewards;
import com.example.RetailRewardsCalculator.service.CustomerService;
import com.example.RetailRewardsCalculator.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired

    CustomerService customerService;

    @GetMapping("/{customerId}")
    public Rewards  getRewards(@PathVariable("customerId") Integer customerId){

        return this.customerService.calculateRewards(customerId);
    }
}
