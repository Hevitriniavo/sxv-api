package com.fresh.coding.schoolmanagementapi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentPaymentDTO implements Serializable {
    private PaymentDTO payment;
    private StudentDTO student;
}
