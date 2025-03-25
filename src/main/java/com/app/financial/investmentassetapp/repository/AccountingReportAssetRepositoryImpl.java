package com.app.financial.investmentassetapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountingReportAssetRepositoryImpl {

    @Autowired
    private IAssetRespository assetRespository;

    public List<Object[]>  findRawAccountingReportAssets(){
        return assetRespository.findRawAccountingReportAssets();
    }
}
