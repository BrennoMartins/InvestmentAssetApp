package com.app.financial.controller;

import com.app.financial.model.AccountingReportAsset;
import com.app.financial.service.asset.AccountingReportAssetService;
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
    public ResponseEntity<List<AccountingReportAsset>> getAccountingReportAsset() {
        if(accountingReportAssetService.getAllAccountingReportAsset().isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(accountingReportAssetService.getAllAccountingReportAsset());
        }
    }
}
