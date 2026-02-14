package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BankRepositoryImpl {

    @Autowired
    private IBankRespository bankRespository;

    public List<Bank> getAllBank(){return bankRespository.findAll();}
    public Optional<Bank> getByBank(String bank) { return bankRespository.findByBank(bank);}
    public Optional<Bank> getBankById(Long id){ return bankRespository.findById(id);}
    public void deleteBankById(Long id){bankRespository.deleteById(id);}
    public void addBank(Bank bank){bankRespository.save(bank);}

}
