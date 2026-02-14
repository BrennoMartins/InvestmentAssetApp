package com.app.financial.investmentassetapp.service;

import com.app.financial.investmentassetapp.model.AssetType;
import com.app.financial.investmentassetapp.repository.AssetTypeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetTypeService {

    @Autowired
    private AssetTypeRepositoryImpl assetTypeRepository;

    public List<AssetType> getAllAssetType() {
        return assetTypeRepository.getAllAssetType();
    }

    public Optional<AssetType> getById(Long id) {
        return assetTypeRepository.getAssetTypeById(id);
    }

    public Optional<AssetType> getByName(String name) {
        return assetTypeRepository.getByName(name);
    }

    public void deleteAssetType(Long id) {
        assetTypeRepository.deleteAssetTypeById(id);
    }

    public void addAssetType(AssetType assetType) {
        assetTypeRepository.addAssetType(assetType);
    }
}
