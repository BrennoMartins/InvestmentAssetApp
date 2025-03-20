package com.app.financial.service.asset;

import com.app.financial.model.Asset;
import com.app.financial.repository.AssetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements IAssetCalculator{

    @Autowired
    private AssetRepositoryImpl assetRepository;

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
        assetRepository.addAsset(calculatedValues(asset));
    }

    public void updateAasset(Asset asset) {
        asset.setQuantity(asset.getQuantity().setScale(8, RoundingMode.HALF_UP));
        assetRepository.updateAsset(calculatedValues(asset));
    }


    @Override
    public BigDecimal differenceCalculate(BigDecimal quotation, BigDecimal averagePrice) {
        return quotation.subtract(averagePrice);
    }

    @Override
    public BigDecimal indexCalculate(BigDecimal quotation, BigDecimal averagePrice) {
        BigDecimal percent = quotation.divide(averagePrice, 2, RoundingMode.HALF_UP).subtract(BigDecimal.ONE);
        return percent.multiply(new BigDecimal("100"));
    }

    @Override
    public BigDecimal valueCalculate(BigDecimal quantity, BigDecimal quotation) {
        return quantity.multiply(quotation);
    }

    public Asset calculatedValues(Asset asset){
        asset.setDifference(differenceCalculate(asset.getQuotation(), asset.getAveragePrice()));
        asset.setIndex(indexCalculate(asset.getQuotation(), asset.getAveragePrice()));
        asset.setValue(valueCalculate(asset.getQuantity(), asset.getQuotation()));
        return asset;
    }

}
