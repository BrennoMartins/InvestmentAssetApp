package com.app.financial.investmentassetapp.controller;


import com.app.financial.investmentassetapp.excpetion.AssetNotFoundException;
import com.app.financial.investmentassetapp.model.Asset;
import com.app.financial.investmentassetapp.service.asset.AssetServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetServiceImpl assetServiceImpl;

    @GetMapping
    public ResponseEntity<List<Asset>> getAsset() {
        if(assetServiceImpl.getAllAsset().isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(assetServiceImpl.getAllAsset());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id ) {
        Optional<Asset> asset = assetServiceImpl.getById(id);
        return asset.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/query")
    public ResponseEntity<Asset> getAssetById(@RequestParam String asset) {
        Optional<Asset> assetParam = assetServiceImpl.getByAsset(asset);
        return assetParam.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Asset> deleteAssetById(@PathVariable Long id ) {
        if(assetServiceImpl.getById(id).isEmpty()){
            throw new AssetNotFoundException("Asset not found");
        }else{
            assetServiceImpl.deleteAssent(id);
            return ResponseEntity.ok().build();
        }

    }

    @PostMapping
    public ResponseEntity<Asset> addAssent(@Valid @RequestBody Asset asset){
        assetServiceImpl.addAssent(asset);
        return ResponseEntity.created(null).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        asset.setId(id);
        assetServiceImpl.updateAasset(asset);
        return ResponseEntity.created(null).build();
    }

}