package com.app.financial.investmentassetapp.external.dto;

import java.math.BigDecimal;

public record AssetSubTypeValueReportDto(Long assetSubTypeId,String assetSubTypeName, BigDecimal totalValue) {
}

