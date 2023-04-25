package com.ahad.salary.management.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "bank_account_info_table")
public class BankAccount {
    @Id
    private Integer number;
    @Column(length = 20)
    private String accountType;
    @Column(length = 30)
    private String accountName;
    private double currentBalance;
    @Column(length = 30)
    private String branchName;

    @OneToOne
    private Employee employee;

    @ManyToOne
    private Bank bank;

    @OneToMany(mappedBy = "account")
    @ToString.Exclude
    private List<Transaction> transaction;
}