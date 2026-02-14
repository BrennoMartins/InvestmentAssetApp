package com.app.financial.investmentassetapp.controller;

import com.app.financial.investmentassetapp.excpetion.AssetNotFoundException;
import com.app.financial.investmentassetapp.model.Bank;
import com.app.financial.investmentassetapp.service.BankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asset/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public ResponseEntity<List<Bank>> getAsset() {
        if(bankService.getAllBank().isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(bankService.getAllBank());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getAssetById(@PathVariable Long id ) {
        Optional<Bank> asset = bankService.getById(id);
        return asset.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/query")
    public ResponseEntity<Bank> getAssetById(@RequestParam String bank) {
        Optional<Bank> bankParam = bankService.getByBank(bank);
        return bankParam.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Bank> deleteAssetById(@PathVariable Long id ) {
        if(bankService.getById(id).isEmpty()){
            throw new AssetNotFoundException("Bank not found");
        }else{
            bankService.deleteBank(id);
            return ResponseEntity.ok().build();
        }

    }

    @PostMapping
    public ResponseEntity<Bank> addAssent(@Valid @RequestBody Bank bank){
        bankService.addBank(bank);
        return ResponseEntity.created(null).build();

    }
}
