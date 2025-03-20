package com.app.financial.service.asset;

import com.app.financial.model.AccountingReportAsset;
import com.app.financial.repository.AccountingReportAssetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingReportAssetService {

    @Autowired
    private AccountingReportAssetRepositoryImpl accountingReportAssetRepository;

    public List<AccountingReportAsset> getAllAccountingReportAsset(){
        return accountingReportAssetRepository.getAllAccountingReportAsset();
    }
}
