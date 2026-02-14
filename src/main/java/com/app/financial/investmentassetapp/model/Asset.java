package com.app.financial.investmentassetapp.model;


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
    @Column(name="quantity_asset", precision = 20, scale = 8, nullable = false)
    private BigDecimal quantity;

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

    @ManyToOne
    private Bank bank;

    @ManyToOne
    private AssetType type;

    @Column(name="monthly_contribution")
    private BigDecimal monthlyContribution;

    @Column(name="value_previous_month")
    private BigDecimal valuePreviousMonth;

    @Column(name="percent_monthly_profit")
    private BigDecimal percentMonthlyProfit;

    @Column(name="value_monthly_profit")
    private BigDecimal valueMonthlyProfit;

    @Column(name="maturation")
    private BigDecimal maturation;

    @Column(name="percent_wallet")
    private BigDecimal percentWallet;

    public Asset(Long id, String asset, BigDecimal quantity, BigDecimal averagePrice, BigDecimal quotation, BigDecimal difference, BigDecimal index, BigDecimal value) {
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



