package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.AssetSubType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAssetSubTypeRespository extends JpaRepository<AssetSubType, Long> {
    Optional<AssetSubType> findByName(String name);

    List<AssetSubType> findByAssetType_Id(Long assetTypeId);
}
