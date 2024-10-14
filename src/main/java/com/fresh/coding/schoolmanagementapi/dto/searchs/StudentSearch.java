package com.fresh.coding.schoolmanagementapi.dto.searchs;

import com.fresh.coding.schoolmanagementapi.emuns.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentSearch {
    private String id;
    private String name;
    private String firstName;
    private String className;
    private String address;
    private Gender gender;
}
