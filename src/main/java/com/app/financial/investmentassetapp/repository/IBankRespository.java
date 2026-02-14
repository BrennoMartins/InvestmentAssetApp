package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBankRespository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByBank(String bank);
}
