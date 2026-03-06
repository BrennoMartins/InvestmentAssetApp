package com.app.financial.investmentassetapp.service.asset;

import com.app.financial.investmentassetapp.external.QuotationExternal;
import com.app.financial.investmentassetapp.external.adapter.AssetCsvAdapter;
import com.app.financial.investmentassetapp.external.calculator.AssetCalculatorImpl;
import com.app.financial.investmentassetapp.model.Asset;
import com.app.financial.investmentassetapp.repository.AssetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void updateAasset(Asset asset) {
        asset.setQuantity(asset.getQuantity().setScale(8, RoundingMode.HALF_UP));
        if(asset.getQuotation() == null){
            asset.setQuotation(new QuotationExternal().returnValueQuotationExternal(asset.getAsset()));}
        assetRepository.updateAsset(new AssetCalculatorImpl().calculatedValues(asset));
    }

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

    public void createAssetBatch() {
        List<Asset> assets = assetCsvAdapter.readAssetBatchFromCsv();

        assets.forEach(asset -> {
            if (asset != null) {
                Optional<Asset> existingAsset = getByAsset(asset.getAsset());

                if (existingAsset.isEmpty()) {
                    addAssent(asset);
                } else {
                    Asset updated = existingAsset.get();
                    updateAasset(updated);
                }
            }
        });
    }

}
