package com.app.financial.investmentassetapp.service;

import com.app.financial.investmentassetapp.model.AssetCategory;
import com.app.financial.investmentassetapp.repository.AssetCategoryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetCategoryService {

    @Autowired
    private AssetCategoryRepositoryImpl assetCategoryRepository;

    public List<AssetCategory> getAllAssetCategory(){return assetCategoryRepository.getAllCategory();}
    public Optional<AssetCategory> getById(Long id) {
        return assetCategoryRepository.getCategoryById(id);
    }
    public Optional<AssetCategory> getByAssetCategory(String assetCategory) {return assetCategoryRepository.getByCategory(assetCategory);}
    public void deleteAssetCategory(Long id) {
        assetCategoryRepository.deleteCategoryById(id);
    }
    public void addAssetCategory(AssetCategory assetCategory) {
        assetCategoryRepository.addCategory(assetCategory);
    }
}
