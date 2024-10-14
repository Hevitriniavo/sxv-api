package com.fresh.coding.schoolmanagementapi.dto;

import com.fresh.coding.schoolmanagementapi.emuns.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentWithPaymentsDTO {
    private String id;
    private String name;
    private String firstName;
    private String className;
    private String address;
    private Gender gender;
    private List<PaymentDTO> payments;
}
