package com.app.financial.investmentassetapp.service.asset;

import com.app.financial.investmentassetapp.model.Asset;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AssetCalculatorImpl implements IAssetCalculator{

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
        if(asset.getQuotation() == null){
            //TODO Buscar Cotação
            asset.setQuotation(BigDecimal.valueOf(0));
        }
        asset.setDifference(differenceCalculate(asset.getQuotation(), asset.getAveragePrice()));
        asset.setIndex(indexCalculate(asset.getQuotation(), asset.getAveragePrice()));
        asset.setValue(valueCalculate(asset.getQuantity(), asset.getQuotation()));
        return asset;
    }
}
