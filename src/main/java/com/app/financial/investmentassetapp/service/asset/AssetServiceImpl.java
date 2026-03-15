package com.app.financial.investmentassetapp.service.asset;

import com.app.financial.investmentassetapp.external.QuotationExternal;
import com.app.financial.investmentassetapp.external.adapter.AssetCsvAdapter;
import com.app.financial.investmentassetapp.external.calculator.AssetCalculatorImpl;
import com.app.financial.investmentassetapp.model.Asset;
import com.app.financial.investmentassetapp.repository.AssetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl{

    @Autowired
    private AssetRepositoryImpl assetRepository;

    @Autowired
    private AssetCsvAdapter assetCsvAdapter;

    public List<Asset> getAllAsset(){
        return assetRepository.getAllAsset();
    }

    public Optional<Asset> getById(Long id) {
        return assetRepository.getAssetById(id);
    }

    public Optional<Asset> getByAsset(String asset) {
        return assetRepository.getAssetByAsset(asset);
    }

    public void deleteAssent(Long id) {
        assetRepository.deleteAssetById(id);
    }

    public void addAssent(Asset asset) {
        asset.setQuantity(asset.getQuantity().setScale(8, RoundingMode.HALF_UP));
        if(asset.getQuotation() == null){
            asset.setQuotation(new QuotationExternal().returnValueQuotationExternal(asset.getAsset()));}
        assetRepository.addAsset(new AssetCalculatorImpl().calculatedValues(asset));
    }

    @Transactional
    public void updateAasset(Asset asset) {
        Optional<Asset> existingAssetOpt = assetRepository.getAssetById(asset.getId());

        if (existingAssetOpt.isEmpty()) {
            throw new RuntimeException("Asset not found with id: " + asset.getId());
        }

        Asset existingAsset = existingAssetOpt.get();

        existingAsset.setAsset(asset.getAsset());
        existingAsset.setQuantity(asset.getQuantity().setScale(8, RoundingMode.HALF_UP));
        existingAsset.setAveragePrice(asset.getAveragePrice());
        existingAsset.setBank(asset.getBank());
        existingAsset.setSubType(asset.getSubType());
        existingAsset.setMonthlyContribution(asset.getMonthlyContribution());
        existingAsset.setValuePreviousMonth(asset.getValuePreviousMonth());
        existingAsset.setMaturation(asset.getMaturation());

        if(asset.getQuotation() == null){
            existingAsset.setQuotation(new QuotationExternal().returnValueQuotationExternal(asset.getAsset()));
        } else {
            existingAsset.setQuotation(asset.getQuotation());
        }

        assetRepository.updateAsset(new AssetCalculatorImpl().calculatedValues(existingAsset));
    }

    @Transactional
    public void updateAssetQuotation() {
        List<Asset> listAssets = assetRepository.getAllAsset();

        if (listAssets == null || listAssets.isEmpty()) {
            return;
        }

        listAssets.forEach(asset -> {
            BigDecimal quotation = new QuotationExternal().returnValueQuotationExternal(asset.getAsset());
            asset.setQuotation(quotation);
            assetRepository.updateAsset(new AssetCalculatorImpl().calculatedValues(asset));
        });
    }

    @Transactional
    public void createAssetBatch() {
        List<Asset> assets = assetCsvAdapter.readAssetBatchFromCsv();

        assets.forEach(asset -> {
            if (asset != null) {
                Optional<Asset> existingAsset = getByAsset(asset.getAsset());

                if (existingAsset.isEmpty()) {
                    addAssent(asset);
                } else {
                    Asset updated = existingAsset.get();
                    updated.setQuantity(asset.getQuantity());
                    updated.setAveragePrice(asset.getAveragePrice());
                    updated.setQuotation(asset.getQuotation());
                    updated.setBank(asset.getBank());
                    updated.setSubType(asset.getSubType());
                    if (asset.getMonthlyContribution() != null) {
                        updated.setMonthlyContribution(asset.getMonthlyContribution());
                    }
                    if (asset.getValuePreviousMonth() != null) {
                        updated.setValuePreviousMonth(asset.getValuePreviousMonth());
                    }
                    if (asset.getMaturation() != null) {
                        updated.setMaturation(asset.getMaturation());
                    }
                    updateAasset(updated);
                }
            }
        });
    }

    public BigDecimal getTotalAssetValueWithStream() {
        return assetRepository.getAllAsset().stream()
                .map(Asset::getValue)
                .filter(java.util.Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    @Transactional
    public void updateWalletPercent() {
        List<Asset> listAssets = assetRepository.getAllAsset();
        BigDecimal totalValue = getTotalAssetValueWithStream();

        listAssets.forEach(asset -> {
            asset.setPercentWallet(new AssetCalculatorImpl().calculatedWalletPercent(totalValue, asset.getValue()));
            assetRepository.updateAsset(asset);
        });
    }

}
