package com.app.financial.investmentassetapp.service.asset;

import com.app.financial.investmentassetapp.model.AccountingReportAsset;
import com.app.financial.investmentassetapp.model.AssetCategory;
import com.app.financial.investmentassetapp.repository.AccountingReportAssetRepositoryImpl;
import com.app.financial.investmentassetapp.service.AssetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
