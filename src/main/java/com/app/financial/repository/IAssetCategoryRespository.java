package com.app.financial.repository;

import com.app.financial.model.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAssetCategoryRespository extends JpaRepository<AssetCategory, Long> {

    Optional<AssetCategory> findByCategory(String category);

}
