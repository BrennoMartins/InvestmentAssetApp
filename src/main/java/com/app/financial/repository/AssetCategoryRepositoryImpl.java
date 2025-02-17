package com.app.financial.repository;

import com.app.financial.model.AssetCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AssetCategoryRepositoryImpl {


    @Autowired
    private IAssetCategoryRespository categoryRespository;

    public List<AssetCategory> getAllCategory(){return categoryRespository.findAll();}
    public Optional<AssetCategory> getByCategory(String assetCategory) { return categoryRespository.findByCategory(assetCategory);}
    public Optional<AssetCategory> getCategoryById(Long id){ return categoryRespository.findById(id);}
    public void deleteCategoryById(Long id){categoryRespository.deleteById(id);}
    public void addCategory(AssetCategory assetCategory){categoryRespository.save(assetCategory);}
}
