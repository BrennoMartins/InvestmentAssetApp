package com.app.financial.investmentassetapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "asset_contribution")
public class AssetContribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contribution")
    private Long id;

    @NotNull(message = "Asset not be null")
    @ManyToOne
    @JoinColumn(name = "id_asset")
    private Asset asset;

    @NotNull(message = "Contribution date not be null")
    @Column(name = "contribution_date")
    private LocalDate contributionDate;

    @NotNull(message = "Value not be null")
    @Column(name = "value", precision = 20, scale = 2)
    private BigDecimal value;

}

