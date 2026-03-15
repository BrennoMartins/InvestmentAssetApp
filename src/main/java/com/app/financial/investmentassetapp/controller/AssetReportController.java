package com.app.financial.investmentassetapp.controller;

import com.app.financial.investmentassetapp.external.dto.AssetSubTypeValueReportDto;
import com.app.financial.investmentassetapp.external.dto.AssetTypeValueReportDto;
import com.app.financial.investmentassetapp.external.dto.AssetValueReportDto;
import com.app.financial.investmentassetapp.service.AssetReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/by-sub-type")
    public ResponseEntity<List<AssetSubTypeValueReportDto>> getSumBySubType(
            @RequestParam(required = false) Long id) {
        List<AssetSubTypeValueReportDto> report = assetReportService.getAssetSubTypeValueReport(id);
        if (report.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(report);
    }

    @GetMapping("{subTypeId}/by-sub-type")
    public ResponseEntity<List<AssetValueReportDto>> getAssetSumBySubType(@PathVariable Long subTypeId) {
        List<AssetValueReportDto> report = assetReportService.findAssetBySubTypeValueReport(subTypeId);
        if (report.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(report);
    }





}

