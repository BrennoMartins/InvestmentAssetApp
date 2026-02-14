package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.AssetSubType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AssetSubTypeRepositoryImpl {

    @Autowired
    private IAssetSubTypeRespository assetSubTypeRespository;

    public List<AssetSubType> getAllAssetSubType() {
        return assetSubTypeRespository.findAll();
    }

    public Optional<AssetSubType> getByName(String name) {
        return assetSubTypeRespository.findByName(name);
    }

    public List<AssetSubType> getByAssetTypeId(Long assetTypeId) {
        return assetSubTypeRespository.findByAssetType_Id(assetTypeId);
    }

    public Optional<AssetSubType> getAssetSubTypeById(Long id) {
        return assetSubTypeRespository.findById(id);
    }

    public void deleteAssetSubTypeById(Long id) {
        assetSubTypeRespository.deleteById(id);
    }

    public void addAssetSubType(AssetSubType assetSubType) {
        assetSubTypeRespository.save(assetSubType);
    }
}
