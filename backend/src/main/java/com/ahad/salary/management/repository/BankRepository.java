package com.ahad.salary.management.repository;

import com.ahad.salary.management.domain.entity.Bank;
import com.ahad.salary.management.response.BankResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank,Integer> {

    @Query("select new com.ahad.salary.management.response.BankResponse(b.id,b.name,b.address,b.country) " +
            "from Bank as b")
    List<BankResponse> getAllBankInformation();

    @Query("select new com.ahad.salary.management.response.BankResponse(b.id,b.name,b.address,b.country) " +
            "from Bank as b " +
            "where b.id = :id ")
    BankResponse getBankInformation(@Param("id") int id);
}
