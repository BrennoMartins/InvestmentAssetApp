package com.app.financial.investmentassetapp.controller;


import com.app.financial.investmentassetapp.excpetion.AssetNotFoundException;
import com.app.financial.investmentassetapp.model.Asset;
import com.app.financial.investmentassetapp.model.AssetCategory;
import com.app.financial.investmentassetapp.service.AssetCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asset/category")
public class AssetCategoryController {

    @Autowired
    private AssetCategoryService assetCategoryService;

    @GetMapping
    public ResponseEntity<List<AssetCategory>> getAsset() {
        if(assetCategoryService.getAllAssetCategory().isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(assetCategoryService.getAllAssetCategory());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetCategory> getAssetById(@PathVariable Long id ) {
        Optional<AssetCategory> asset = assetCategoryService.getById(id);
        return asset.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/query")
    public ResponseEntity<AssetCategory> getAssetById(@RequestParam String category) {
        Optional<AssetCategory> assetParam = assetCategoryService.getByAssetCategory(category);
        return assetParam.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<AssetCategory> deleteAssetById(@PathVariable Long id ) {
        if(assetCategoryService.getById(id).isEmpty()){
            throw new AssetNotFoundException("Asset not found");
        }else{
            assetCategoryService.deleteAssetCategory(id);
            return ResponseEntity.ok().build();
        }

    }

    @PostMapping
    public ResponseEntity<Asset> addAssent(@Valid @RequestBody AssetCategory assetCategory){
        assetCategoryService.addAssetCategory(assetCategory);
        return ResponseEntity.created(null).build();

    }

}