package com.fresh.coding.schoolmanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TotalPaymentsByClassDTO {
    private String className;
    private Long totalAmount;
}
