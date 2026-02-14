package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AssetTypeRepositoryImpl {

    @Autowired
    private IAssetTypeRespository assetTypeRespository;

    public List<AssetType> getAllAssetType() {
        return assetTypeRespository.findAll();
    }

    public Optional<AssetType> getByName(String name) {
        return assetTypeRespository.findByName(name);
    }

    public Optional<AssetType> getAssetTypeById(Long id) {
        return assetTypeRespository.findById(id);
    }

    public void deleteAssetTypeById(Long id) {
        assetTypeRespository.deleteById(id);
    }

    public void addAssetType(AssetType assetType) {
        assetTypeRespository.save(assetType);
    }
}
