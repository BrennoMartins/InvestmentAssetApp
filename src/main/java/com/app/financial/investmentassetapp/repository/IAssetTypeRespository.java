package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAssetTypeRespository extends JpaRepository<AssetType, Long> {
    Optional<AssetType> findByName(String name);
}
