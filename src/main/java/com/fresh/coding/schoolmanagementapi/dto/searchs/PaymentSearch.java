package com.fresh.coding.schoolmanagementapi.dto.searchs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSearch {
    private String paymentName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer month;
}