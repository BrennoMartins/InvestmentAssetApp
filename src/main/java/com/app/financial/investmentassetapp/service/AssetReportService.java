package com.app.financial.investmentassetapp.service;

import com.app.financial.investmentassetapp.external.dto.AssetSubTypeValueReportDto;
import com.app.financial.investmentassetapp.external.dto.AssetTypeValueReportDto;
import com.app.financial.investmentassetapp.repository.IAssetRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetReportService {

    private final IAssetRespository assetRespository;

    public AssetReportService(IAssetRespository assetRespository) {
        this.assetRespository = assetRespository;
    }

    public List<AssetTypeValueReportDto> getAssetTypeValueReport() {
        return assetRespository.findAssetTypeValueReport();
    }

    public List<AssetSubTypeValueReportDto> getAssetSubTypeValueReport(Long assetTypeId) {
        return assetRespository.findAssetSubTypeValueReport(assetTypeId);
    }
}

