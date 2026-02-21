package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.AssetContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AssetContributionRepositoryImpl {

    @Autowired
    private IAssetContributionRepository assetContributionRepository;

    public List<AssetContribution> getAllAssetContribution() {
        return assetContributionRepository.findAll();
    }

    public Optional<AssetContribution> getAssetContributionById(Long id) {
        return assetContributionRepository.findById(id);
    }

    public List<AssetContribution> getContributionsByAssetId(Long assetId) {
        return assetContributionRepository.findByAssetId(assetId);
    }

    public void addAssetContribution(AssetContribution assetContribution) {
        assetContributionRepository.save(assetContribution);
    }

    public void updateAssetContribution(AssetContribution assetContribution) {
        assetContributionRepository.save(assetContribution);
    }

    public void deleteAssetContributionById(Long id) {
        assetContributionRepository.deleteById(id);
    }

}

