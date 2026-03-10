package com.app.financial.investmentassetapp.external.dto;

import java.math.BigDecimal;

public record AssetTypeValueReportDto(Long assetTypeId, String assetTypeName, BigDecimal totalValue) {
}

