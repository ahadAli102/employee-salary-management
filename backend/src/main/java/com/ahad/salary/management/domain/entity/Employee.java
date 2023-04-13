package com.ahad.salary.management.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee_table")
public class Employee {

    @Id
    private Integer id;
    @Column(length = 30)
    private String name;
    private int grade;

    @Embedded
    private Address address;
    @Column(length = 15)
    private String mobile;

    @OneToOne(mappedBy = "employee")
    private BankAccount bankAccount;

}