package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.InvestmentsContribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IInvestmentsContributionRepository extends JpaRepository<InvestmentsContribution, Long> {
}

