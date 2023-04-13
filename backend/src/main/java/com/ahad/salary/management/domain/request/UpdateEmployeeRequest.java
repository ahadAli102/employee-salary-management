package com.ahad.salary.management.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {

    private String name;
    private Integer grade;
    private String house;
    private String street;
    private String city;
    private String state;
    private String country;
    private String mobile;
}
