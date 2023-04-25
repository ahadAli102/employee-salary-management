package com.ahad.salary.management.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "account_transaction_info_table")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double amount;
    private String type;
    private LocalDateTime time;

    @ManyToOne
    BankAccount account;
}
