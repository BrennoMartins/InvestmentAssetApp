package com.app.financial.investmentassetapp.service.asset;

import java.math.BigDecimal;

public interface IAssetCalculator {

    BigDecimal differenceCalculate(BigDecimal quotation, BigDecimal averagePrice);

    BigDecimal indexCalculate(BigDecimal quotation, BigDecimal averagePrice);

    BigDecimal valueCalculate(BigDecimal quantity, BigDecimal quotation);
}
