package com.app.financial.investmentassetapp.service;

import com.app.financial.investmentassetapp.model.Bank;
import com.app.financial.investmentassetapp.repository.BankRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepositoryImpl bankRepository;

    public List<Bank> getAllBank(){return bankRepository.getAllBank();}
    public Optional<Bank> getById(Long id) {
        return bankRepository.getBankById(id);
    }
    public Optional<Bank> getByBank(String name) {return bankRepository.getByBank(name);}
    public void deleteBank(Long id) {
        bankRepository.deleteBankById(id);
    }
    public void addBank(Bank bank) {
        bankRepository.addBank(bank);
    }
}
