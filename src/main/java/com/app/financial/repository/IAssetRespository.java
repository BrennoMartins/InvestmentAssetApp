package com.app.financial.repository;

import com.app.financial.model.AccountingReportAsset;
import com.app.financial.model.Asset;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IAssetRespository extends JpaRepository<Asset, Long> {

    Optional<Asset> findByAsset(String asset);

    @Query(nativeQuery = true, value = "select asset_category_id , sum(value_asset) as valor from asset inner join asset_category on (asset_category_id = id) group by 1 order by 2 desc")
    //List<Map<Integer, Object>> findRawAccountingReportAssets();
    List<Object[]> findRawAccountingReportAssets();

}
