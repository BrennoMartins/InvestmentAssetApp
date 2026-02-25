package com.app.financial.investmentassetapp.controller;

import com.app.financial.investmentassetapp.excpetion.AssetNotFoundException;
import com.app.financial.investmentassetapp.model.AssetContribution;
import com.app.financial.investmentassetapp.service.AssetContributionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asset/contribution")
public class AssetContributionController {

    @Autowired
    private AssetContributionService assetContributionService;

    @GetMapping
    public ResponseEntity<List<AssetContribution>> getAllAssetContribution() {
        List<AssetContribution> contributions = assetContributionService.getAllAssetContribution();
        if (contributions.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(contributions);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetContribution> getAssetContributionById(@PathVariable Long id) {
        Optional<AssetContribution> contribution = assetContributionService.getAssetContributionById(id);
        return contribution.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetContribution>> getContributionsByAssetId(@PathVariable Long assetId) {
        List<AssetContribution> contributions = assetContributionService.getContributionsByAssetId(assetId);
        if (contributions.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(contributions);
        }
    }

    @PostMapping
    public ResponseEntity<AssetContribution> addAssetContribution(@Valid @RequestBody AssetContribution assetContribution) {
        assetContributionService.addAssetContribution(assetContribution);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetContribution> updateAssetContribution(
            @PathVariable Long id,
            @Valid @RequestBody AssetContribution assetContribution) {
        if (assetContributionService.getAssetContributionById(id).isEmpty()) {
            throw new AssetNotFoundException("Asset Contribution not found");
        }
        assetContribution.setId(id);
        assetContributionService.updateAssetContribution(assetContribution);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssetContributionById(@PathVariable Long id) {
        if (assetContributionService.getAssetContributionById(id).isEmpty()) {
            throw new AssetNotFoundException("Asset Contribution not found");
        }
        assetContributionService.deleteAssetContributionById(id);
        return ResponseEntity.ok().build();
    }

}

