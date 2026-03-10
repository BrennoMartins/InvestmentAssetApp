package com.app.financial.investmentassetapp.controller;

import com.app.financial.investmentassetapp.external.dto.AssetSubTypeValueReportDto;
import com.app.financial.investmentassetapp.external.dto.AssetTypeValueReportDto;
import com.app.financial.investmentassetapp.service.AssetReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports/assets")
public class AssetReportController {

    private final AssetReportService assetReportService;

    public AssetReportController(AssetReportService assetReportService) {
        this.assetReportService = assetReportService;
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<AssetTypeValueReportDto>> getSumByType() {
        List<AssetTypeValueReportDto> report = assetReportService.getAssetTypeValueReport();
        if (report.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(report);
    }

    @GetMapping("/by-sub-type/{id}")
    public ResponseEntity<List<AssetSubTypeValueReportDto>> getSumBySubType(@PathVariable Long id) {
        List<AssetSubTypeValueReportDto> report = assetReportService.getAssetSubTypeValueReport(id);
        if (report.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(report);
    }
}

