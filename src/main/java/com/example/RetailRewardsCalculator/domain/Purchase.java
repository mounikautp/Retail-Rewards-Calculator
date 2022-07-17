package com.example.RetailRewardsCalculator.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class Purchase {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty
    private String customerName;

    @Min(value = 1)
    private double spendValue;

    @PastOrPresent
    private LocalDate purchaseDate;
}
