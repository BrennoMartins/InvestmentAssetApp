package com.app.financial.investmentassetapp.controller;

import com.app.financial.investmentassetapp.model.AccountingReportAsset;
import com.app.financial.investmentassetapp.service.asset.AccountingReportAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asset/report")
public class AssetReportController {

    @Autowired
    private AccountingReportAssetService accountingReportAssetService;


    @GetMapping("/accounting")
    public ResponseEntity<List<AccountingReportAsset>> findRawAccountingReportAssets() {
        if(accountingReportAssetService.findRawAccountingReportAssets().isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(accountingReportAssetService.findRawAccountingReportAssets());
        }
    }
}
