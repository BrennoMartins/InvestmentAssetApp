package com.app.financial.investmentassetapp.repository;

import com.app.financial.investmentassetapp.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AssetRepositoryImpl {


    @Autowired
    private IAssetRespository assetRespository;
    //private List<Asset> assetList = new ArrayList<>();

    public List<Asset> getAllAsset(){
        return assetRespository.findAll();
    }

    public Optional<Asset> getAssetByAsset(String asset){
        return assetRespository.findByAsset(asset);

    }
    public Optional<Asset> getAssetById(Long id){
        //Predicate<? super Asset> predicate = i -> i.getName().equals(name);
/*        return assetList.stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElse(null);*/

        return assetRespository.findById(id);

    }

    public void deleteAssetById(Long id){
        //Predicate<? super Asset> predicate = i -> i.getName().equals(name);
        assetRespository.deleteById(id);
    }

    public void addAsset(Asset asset){
        assetRespository.save(asset);
    }

    public void updateAsset(Asset asset) {assetRespository.save(asset);}
}
