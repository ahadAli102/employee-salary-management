package com.ahad.salary.management.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvideSalaryResponse {
    private double requiredAmount;
    private String message;
}
