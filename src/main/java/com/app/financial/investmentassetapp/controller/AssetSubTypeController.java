package com.app.financial.investmentassetapp.controller;

import com.app.financial.investmentassetapp.excpetion.AssetNotFoundException;
import com.app.financial.investmentassetapp.model.AssetSubType;
import com.app.financial.investmentassetapp.service.AssetSubTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asset/asset-sub-type")
public class AssetSubTypeController {

    @Autowired
    private AssetSubTypeService assetSubTypeService;

    @GetMapping
    public ResponseEntity<List<AssetSubType>> getAssetSubType() {
        if (assetSubTypeService.getAllAssetSubType().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(assetSubTypeService.getAllAssetSubType());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetSubType> getAssetSubTypeById(@PathVariable Long id) {
        Optional<AssetSubType> assetSubType = assetSubTypeService.getById(id);
        return assetSubType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/query")
    public ResponseEntity<AssetSubType> getAssetSubTypeByName(@RequestParam String name) {
        Optional<AssetSubType> assetSubType = assetSubTypeService.getByName(name);
        return assetSubType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-asset-type/{assetTypeId}")
    public ResponseEntity<List<AssetSubType>> getAssetSubTypeByAssetTypeId(@PathVariable Long assetTypeId) {
        List<AssetSubType> list = assetSubTypeService.getByAssetTypeId(assetTypeId);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AssetSubType> deleteAssetSubTypeById(@PathVariable Long id) {
        if (assetSubTypeService.getById(id).isEmpty()) {
            throw new AssetNotFoundException("AssetSubType not found");
        } else {
            assetSubTypeService.deleteAssetSubType(id);
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping
    public ResponseEntity<AssetSubType> addAssetSubType(@Valid @RequestBody AssetSubType assetSubType) {
        assetSubTypeService.addAssetSubType(assetSubType);
        return ResponseEntity.created(null).build();
    }
}
