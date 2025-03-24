package com.app.financial.repository;

import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AccountingReportAssetRepositoryImpl {

    @Autowired
    private IAssetRespository assetRespository;

    public List<Object[]>  findRawAccountingReportAssets(){
        return assetRespository.findRawAccountingReportAssets();
    }
}
