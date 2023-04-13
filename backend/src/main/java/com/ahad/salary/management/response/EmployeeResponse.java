package com.ahad.salary.management.response;

import com.ahad.salary.management.domain.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Integer id;
    private String name;
    private int grade;
    private Address address;
    private String mobile;
}
