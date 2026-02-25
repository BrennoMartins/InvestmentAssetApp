package com.app.financial.investmentassetapp.service;

import com.app.financial.investmentassetapp.model.AssetContribution;
import com.app.financial.investmentassetapp.repository.AssetContributionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetContributionService {

    @Autowired
    private AssetContributionRepositoryImpl assetContributionRepository;

    public List<AssetContribution> getAllAssetContribution() {
        return assetContributionRepository.getAllAssetContribution();
    }

    public Optional<AssetContribution> getAssetContributionById(Long id) {
        return assetContributionRepository.getAssetContributionById(id);
    }

    public List<AssetContribution> getContributionsByAssetId(Long assetId) {
        return assetContributionRepository.getContributionsByAssetId(assetId);
    }

    public void addAssetContribution(AssetContribution assetContribution) {
        assetContributionRepository.addAssetContribution(assetContribution);
    }

    public void updateAssetContribution(AssetContribution assetContribution) {
        assetContributionRepository.updateAssetContribution(assetContribution);
    }

    public void deleteAssetContributionById(Long id) {
        assetContributionRepository.deleteAssetContributionById(id);
    }

}

