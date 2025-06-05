package com.app.financial.investmentassetapp.external.dto.quotation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class QuotationDTO {
    private Long id;
    private String name;
    private BigDecimal value;
    private LocalDateTime dateLastUpdate;
    private Boolean automaticUpdateValue;
}
