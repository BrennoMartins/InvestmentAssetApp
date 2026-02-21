package com.app.financial.investmentassetapp.controller;

import com.app.financial.investmentassetapp.excpetion.AssetNotFoundException;
import com.app.financial.investmentassetapp.model.InvestmentsContribution;
import com.app.financial.investmentassetapp.service.InvestmentsContributionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asset/investments-contribution")
public class InvestmentsContributionController {

    @Autowired
    private InvestmentsContributionService investmentsContributionService;

    @GetMapping
    public ResponseEntity<List<InvestmentsContribution>> getAllInvestmentsContribution() {
        List<InvestmentsContribution> contributions = investmentsContributionService.getAllInvestmentsContribution();
        if (contributions.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(contributions);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentsContribution> getInvestmentsContributionById(@PathVariable Long id) {
        Optional<InvestmentsContribution> contribution = investmentsContributionService.getInvestmentsContributionById(id);
        return contribution.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InvestmentsContribution> addInvestmentsContribution(@Valid @RequestBody InvestmentsContribution investmentsContribution) {
        investmentsContributionService.addInvestmentsContribution(investmentsContribution);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestmentsContribution> updateInvestmentsContribution(
            @PathVariable Long id,
            @Valid @RequestBody InvestmentsContribution investmentsContribution) {
        if (investmentsContributionService.getInvestmentsContributionById(id).isEmpty()) {
            throw new AssetNotFoundException("Investments Contribution not found");
        }
        investmentsContribution.setId(id);
        investmentsContributionService.updateInvestmentsContribution(investmentsContribution);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestmentsContributionById(@PathVariable Long id) {
        if (investmentsContributionService.getInvestmentsContributionById(id).isEmpty()) {
            throw new AssetNotFoundException("Investments Contribution not found");
        }
        investmentsContributionService.deleteInvestmentsContributionById(id);
        return ResponseEntity.ok().build();
    }

}

