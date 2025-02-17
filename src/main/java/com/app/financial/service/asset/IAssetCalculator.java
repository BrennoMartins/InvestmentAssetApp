package com.app.financial.service.asset;

import java.math.BigDecimal;

public interface IAssetCalculator {

    BigDecimal differenceCalculate(BigDecimal quotation, BigDecimal averagePrice);

    BigDecimal indexCalculate(BigDecimal quotation, BigDecimal averagePrice);

    BigDecimal valueCalculate(Integer quantity, BigDecimal quotation);
}
