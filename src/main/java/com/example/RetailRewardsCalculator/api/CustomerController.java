package com.example.RetailRewardsCalculator.api;

import com.example.RetailRewardsCalculator.domain.Rewards;
import com.example.RetailRewardsCalculator.service.CustomerService;
import com.example.RetailRewardsCalculator.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired

    CustomerService customerService;

    @GetMapping("/{customerId}/rewards")
    public Rewards getRewards(@PathVariable("customerId") Integer customerId) throws Exception {

        return this.customerService.calculateRewards(customerId);
    }

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
