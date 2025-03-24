package com.app.financial.service.asset;

import com.app.financial.model.AccountingReportAsset;
import com.app.financial.model.AssetCategory;
import com.app.financial.repository.AccountingReportAssetRepositoryImpl;
import com.app.financial.service.AssetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AccountingReportAssetService {

    @Autowired
    private AccountingReportAssetRepositoryImpl accountingReportAssetRepository;

    @Autowired
    private AssetCategoryService assetCategoryService;


    public List<AccountingReportAsset> findRawAccountingReportAssets() {
        List<AccountingReportAsset> accountingReportAsset = new ArrayList<>();
        accountingReportAssetRepository.findRawAccountingReportAssets()
                .stream()
                .map(this::getAccountingReportAsset)
                .forEach(accountingReportAsset::add);
        return accountingReportAsset;
    }

    private AccountingReportAsset getAccountingReportAsset(Object[] returnQueryAccountingReportAssetRepositoryObject) {
        AssetCategory assetCategory = assetCategoryService.getById((long) Integer.parseInt(returnQueryAccountingReportAssetRepositoryObject[0].toString())).orElse(new AssetCategory());
        return new AccountingReportAsset(assetCategory,
                new BigDecimal(returnQueryAccountingReportAssetRepositoryObject[1].toString()));
    }

}
