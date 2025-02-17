package com.app.financial.repository;

import com.app.financial.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAssetRespository extends JpaRepository<Asset, Long> {

    Optional<Asset> findByAsset(String asset);

}
