package com.app.financial.investmentassetapp.service.asset;

import com.app.financial.investmentassetapp.external.QuotationExternal;
import com.app.financial.investmentassetapp.model.Asset;
import com.app.financial.investmentassetapp.model.AssetSubType;
import com.app.financial.investmentassetapp.model.Bank;
import com.app.financial.investmentassetapp.repository.AssetRepositoryImpl;
import com.app.financial.investmentassetapp.service.AssetSubTypeService;
import com.app.financial.investmentassetapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class AssetServiceImpl{

    @Autowired
    private AssetRepositoryImpl assetRepository;

    public List<Asset> getAllAsset(){
        return assetRepository.getAllAsset();
    }

    public Optional<Asset> getById(Long id) {
        return assetRepository.getAssetById(id);
    }

    public Optional<Asset> getByAsset(String asset) {
        return assetRepository.getAssetByAsset(asset);
    }

    public void deleteAssent(Long id) {
        assetRepository.deleteAssetById(id);
    }

    public void addAssent(Asset asset) {
        asset.setQuantity(asset.getQuantity().setScale(8, RoundingMode.HALF_UP));
        if(asset.getQuotation() == null){
            asset.setQuotation(new QuotationExternal().returnValueQuotationExternal(asset.getAsset()));}
        assetRepository.addAsset(new AssetCalculatorImpl().calculatedValues(asset));
    }

    public void updateAasset(Asset asset) {
        asset.setQuantity(asset.getQuantity().setScale(8, RoundingMode.HALF_UP));
        if(asset.getQuotation() == null){
            asset.setQuotation(new QuotationExternal().returnValueQuotationExternal(asset.getAsset()));}
        assetRepository.updateAsset(new AssetCalculatorImpl().calculatedValues(asset));
    }

    public void updateAssetQuotation() {
        List<Asset> listAssets = assetRepository.getAllAsset();

        if (listAssets == null || listAssets.isEmpty()) {
            return;
        }

        listAssets.forEach(asset -> {
            BigDecimal quotation = new QuotationExternal().returnValueQuotationExternal(asset.getAsset());
            asset.setQuotation(quotation);
            assetRepository.updateAsset(new AssetCalculatorImpl().calculatedValues(asset));
        });
    }

    //TODO move this in adapter

    public List<Asset> readAssetBatchFromCsv() {
        String csvFilePath = "src/main/resources/asset-batch.csv";
        List<Asset> assets = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(csvFilePath))))) {
            assets = br.lines()
                    .map(this::parseAssetFromCsvLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo CSV: " + e.getMessage());
        }

        return assets;
    }

    @Autowired
    private BankService bankService;

    @Autowired
    private AssetSubTypeService assetSubTypeService;

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
            System.err.println("Erro ao parsear linha CSV: " + line);
            return null;
        }
    }

    public void createAssetBatch() {
        List<Asset> assets = readAssetBatchFromCsv();

        assets.forEach(asset -> {
            if (asset != null) {
                Optional<Asset> existingAsset = getByAsset(asset.getAsset());

                if (existingAsset.isEmpty()) {
                    addAssent(asset);
                } else {
                    Asset updated = existingAsset.get();
                    updated.setQuantity(updated.getQuantity().add(asset.getQuantity()).setScale(8, RoundingMode.HALF_UP));
                    updated.setQuotation(asset.getQuotation());
                    updateAasset(updated);
                }
            }
        });
    }


}
