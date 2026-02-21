package com.app.financial.investmentassetapp.service;

import com.app.financial.investmentassetapp.model.InvestmentsContribution;
import com.app.financial.investmentassetapp.repository.InvestmentsContributionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestmentsContributionService {

    @Autowired
    private InvestmentsContributionRepositoryImpl investmentsContributionRepository;

    public List<InvestmentsContribution> getAllInvestmentsContribution() {
        return investmentsContributionRepository.getAllInvestmentsContribution();
    }

    public Optional<InvestmentsContribution> getInvestmentsContributionById(Long id) {
        return investmentsContributionRepository.getInvestmentsContributionById(id);
    }

    public void addInvestmentsContribution(InvestmentsContribution investmentsContribution) {
        investmentsContributionRepository.addInvestmentsContribution(investmentsContribution);
    }

    public void updateInvestmentsContribution(InvestmentsContribution investmentsContribution) {
        investmentsContributionRepository.updateInvestmentsContribution(investmentsContribution);
    }

    public void deleteInvestmentsContributionById(Long id) {
        investmentsContributionRepository.deleteInvestmentsContributionById(id);
    }

}

