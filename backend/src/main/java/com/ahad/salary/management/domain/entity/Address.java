package com.ahad.salary.management.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Address {
    @Column(length = 15)
    private String house;
    @Column(length = 30)
    private String street;
    @Column(length = 30)
    private String city;
    @Column(length = 30)
    private String state;
    @Column(length = 30)
    private String country;
}
