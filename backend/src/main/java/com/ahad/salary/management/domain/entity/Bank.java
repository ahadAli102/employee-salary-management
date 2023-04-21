package com.ahad.salary.management.domain.entity;

import com.ahad.salary.management.domain.response.AddBankAccountResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="bank_info_table")
public class Bank {
    @Id
    private Integer id;
    @Column(length = 50)
    private String name;
    @Column(length = 100)
    private String address;
    @Column(length = 50)
    private String country;

    @OneToMany(mappedBy="bank")
    @ToString.Exclude
    private Set<BankAccount> bankAccount;
}
