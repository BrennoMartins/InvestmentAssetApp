package com.app.financial.investmentassetapp.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class QuotationExternal {


    RestTemplate restTemplate = new RestTemplate();

    public Object returnAllDataFinanceHgBrasil() {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8085/hgbrasil/finance")
                .encode()
                .build()
                .toUri();

        return restTemplate.getForObject(uri, Object.class);
    }



}
