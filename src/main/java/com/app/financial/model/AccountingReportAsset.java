package com.app.financial.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AccountingReportAsset {

    private String assetCategory;
    private BigDecimal value;

    public AccountingReportAsset(String assetCategory, BigDecimal value) {
        this.assetCategory = assetCategory;
        this.value = value;
    }
}
