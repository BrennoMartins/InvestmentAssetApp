package com.app.financial.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Asset {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_asset")
    private Long id;

    @NotNull(message = "Name asset not be null")
    //@JsonProperty("asset")
    @Column(name="name_asset")
    private String asset;

    //@JsonIgnore
    @NotNull(message = "Quantity asset not be null")
    @Column(name="quantity_asset")
    private Integer quantity;

    @Column(name="value_avarage_price_asset")
    private BigDecimal averagePrice;

    @NotNull(message = "Quotation asset not be null")
    @Column(name="quotation_asset")
    private BigDecimal quotation;

    @Column(name="difference_asset")
    private BigDecimal difference;

    @Column(name="index_asset")
    private BigDecimal index;

    @Column(name="value_asset")
    private BigDecimal value;

    @ManyToOne
    private AssetCategory assetCategory;

    public Asset(Long id, String asset, Integer quantity, BigDecimal averagePrice, BigDecimal quotation, BigDecimal difference, BigDecimal index, BigDecimal value) {
        this.id = id;
        this.asset = asset;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
        this.quotation = quotation;
        this.difference = difference;
        this.index = index;
        this.value = value;
    }
}
