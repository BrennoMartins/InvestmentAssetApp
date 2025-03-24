package com.app.financial.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountingReportAsset {

    private AssetCategory assetCategoryId;
    private BigDecimal value;


}
