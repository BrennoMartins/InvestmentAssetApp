package com.app.financial.investmentassetapp.service;

import com.app.financial.investmentassetapp.model.AssetSubType;
import com.app.financial.investmentassetapp.repository.AssetSubTypeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetSubTypeService {

    @Autowired
    private AssetSubTypeRepositoryImpl assetSubTypeRepository;

    public List<AssetSubType> getAllAssetSubType() {
        return assetSubTypeRepository.getAllAssetSubType();
    }

    public Optional<AssetSubType> getById(Long id) {
        return assetSubTypeRepository.getAssetSubTypeById(id);
    }

    public Optional<AssetSubType> getByName(String name) {
        return assetSubTypeRepository.getByName(name);
    }

    public List<AssetSubType> getByAssetTypeId(Long assetTypeId) {
        return assetSubTypeRepository.getByAssetTypeId(assetTypeId);
    }

    public void deleteAssetSubType(Long id) {
        assetSubTypeRepository.deleteAssetSubTypeById(id);
    }

    public void addAssetSubType(AssetSubType assetSubType) {
        assetSubTypeRepository.addAssetSubType(assetSubType);
    }
}
