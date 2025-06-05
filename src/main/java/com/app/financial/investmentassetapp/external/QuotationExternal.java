package com.app.financial.investmentassetapp.external;

import com.app.financial.investmentassetapp.external.dto.quotation.QuotationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class QuotationExternal {


    RestTemplate restTemplate = new RestTemplate();


    public BigDecimal returnValueQuotationExternal(String nameAsset){
        System.out.println(returnQuotationAsList());
        return returnQuotationAsList().stream()
                .filter(a -> a.getName().equals(nameAsset))
                .map(QuotationDTO::getValue)
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }



    public List<QuotationDTO> returnQuotationAsList() {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8084/app/quotation")
                .encode()
                .build()
                .toUri();

        QuotationDTO[] array = restTemplate.getForObject(uri, QuotationDTO[].class);
        return Arrays.asList(array); // transforma em List<QuotationDTO>
    }




}
