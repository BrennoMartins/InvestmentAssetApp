package com.app.financial.repository;

import com.app.financial.model.AccountingReportAsset;
import com.app.financial.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAssetRespository extends JpaRepository<Asset, Long> {

    Optional<Asset> findByAsset(String asset);

    @Query(nativeQuery = true, value = "select 'A',123")
    List<AccountingReportAsset> getAccountingReportAsset();


}
