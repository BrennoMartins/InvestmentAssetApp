package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.InvestmentsContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InvestmentsContributionRepositoryImpl {

    @Autowired
    private IInvestmentsContributionRepository investmentsContributionRepository;

    public List<InvestmentsContribution> getAllInvestmentsContribution() {
        return investmentsContributionRepository.findAll();
    }

    public Optional<InvestmentsContribution> getInvestmentsContributionById(Long id) {
        return investmentsContributionRepository.findById(id);
    }

    public void addInvestmentsContribution(InvestmentsContribution investmentsContribution) {
        investmentsContributionRepository.save(investmentsContribution);
    }

    public void updateInvestmentsContribution(InvestmentsContribution investmentsContribution) {
        investmentsContributionRepository.save(investmentsContribution);
    }

    public void deleteInvestmentsContributionById(Long id) {
        investmentsContributionRepository.deleteById(id);
    }

}

