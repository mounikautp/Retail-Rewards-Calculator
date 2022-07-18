package com.example.RetailRewardsCalculator;

import com.example.RetailRewardsCalculator.domain.Purchase;
import com.example.RetailRewardsCalculator.repository.PurchaseRepository;
import com.example.RetailRewardsCalculator.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication
public class RetailRewardsCalculatorApplication implements CommandLineRunner {

	@Autowired
	private PurchaseService purchaseService;
	public static void main(String[] args) {
		SpringApplication.run(RetailRewardsCalculatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(int i=0; i < 5; i++){
			Purchase purchase=new Purchase();
			purchase.setCustomerId(1);
			purchase.setCustomerName("Sample Customer");
			purchase.setSpendValue(i+98);
			purchase.setPurchaseDate(Calendar.getInstance().getTime());
			purchaseService.savePurchase(purchase);
		}
	}
}
