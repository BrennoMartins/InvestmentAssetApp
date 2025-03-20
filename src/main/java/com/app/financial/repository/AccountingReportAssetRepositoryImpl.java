package com.app.financial.repository;

import com.app.financial.model.AccountingReportAsset;
import com.app.financial.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountingReportAssetRepositoryImpl {

    @Autowired
    private IAssetRespository assetRespository;

    public List<AccountingReportAsset> getAllAccountingReportAsset(){
        return assetRespository.getAccountingReportAsset();
    }
}
