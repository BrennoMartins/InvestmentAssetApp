package com.app.financial.investmentassetapp.controller;

import com.app.financial.investmentassetapp.excpetion.AssetNotFoundException;
import com.app.financial.investmentassetapp.model.AssetType;
import com.app.financial.investmentassetapp.service.AssetTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asset/asset-type")
public class AssetTypeController {

    @Autowired
    private AssetTypeService assetTypeService;

    @GetMapping
    public ResponseEntity<List<AssetType>> getAssetType() {
        if (assetTypeService.getAllAssetType().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(assetTypeService.getAllAssetType());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetType> getAssetTypeById(@PathVariable Long id) {
        Optional<AssetType> assetType = assetTypeService.getById(id);
        return assetType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/query")
    public ResponseEntity<AssetType> getAssetTypeByName(@RequestParam String name) {
        Optional<AssetType> assetType = assetTypeService.getByName(name);
        return assetType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AssetType> deleteAssetTypeById(@PathVariable Long id) {
        if (assetTypeService.getById(id).isEmpty()) {
            throw new AssetNotFoundException("AssetType not found");
        } else {
            assetTypeService.deleteAssetType(id);
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping
    public ResponseEntity<AssetType> addAssetType(@Valid @RequestBody AssetType assetType) {
        assetTypeService.addAssetType(assetType);
        return ResponseEntity.created(null).build();
    }
}
