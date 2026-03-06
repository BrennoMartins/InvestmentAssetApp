package com.app.financial.investmentassetapp.external.adapter;

import com.app.financial.investmentassetapp.model.Asset;
import com.app.financial.investmentassetapp.model.AssetSubType;
import com.app.financial.investmentassetapp.model.Bank;
import com.app.financial.investmentassetapp.service.AssetSubTypeService;
import com.app.financial.investmentassetapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AssetCsvAdapter {

    @Autowired
    private BankService bankService;

    @Autowired
    private AssetSubTypeService assetSubTypeService;

    public List<Asset> readAssetBatchFromCsv() {
        String csvFilePath = "src/main/resources/asset-batch.csv";
        List<Asset> assets = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(csvFilePath))))) {
            assets = br.lines()
                    .map(this::parseAssetFromCsvLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error read CSV: " + e.getMessage());
        }

        return assets;
    }

    private Asset parseAssetFromCsvLine(String line) {
        String[] values = line.split(",");

        if (values.length < 6) {
            return null;
        }

        try {
            Asset asset = new Asset();
            asset.setAsset(values[0].trim());
            asset.setQuantity(new BigDecimal(values[1].trim()).setScale(8, RoundingMode.HALF_UP));
            asset.setAveragePrice(new BigDecimal(values[2].trim()).setScale(2, RoundingMode.HALF_UP));
            asset.setQuotation(new BigDecimal(values[3].trim()).setScale(2, RoundingMode.HALF_UP));

            Long bankId = Long.parseLong(values[4].trim());
            Optional<Bank> bank = bankService.getById(bankId);
            bank.ifPresent(asset::setBank);

            Long subTypeId = Long.parseLong(values[5].trim());
            Optional<AssetSubType> subType = assetSubTypeService.getById(subTypeId);
            subType.ifPresent(asset::setSubType);

            return asset;

        } catch (NumberFormatException e) {
            System.err.println("error parse CSV: " + line);
            return null;
        }
    }
}

