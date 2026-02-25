package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.AssetContribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAssetContributionRepository extends JpaRepository<AssetContribution, Long> {
    List<AssetContribution> findByAssetId(Long assetId);
}

