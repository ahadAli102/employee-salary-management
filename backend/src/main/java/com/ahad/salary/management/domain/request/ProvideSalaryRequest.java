package com.ahad.salary.management.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvideSalaryRequest {

    private int bankAccountId;
    private double lowerSalary;
    private double totalSalary;
}
