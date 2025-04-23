package com.app.financial.investmentassetapp;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/*
    SWAGGER: http://localhost:8080/v3/api-docs
             http://localhost:8080/asset/swagger-ui/index.html#/

    ACTUATOR - http://localhost:8080/actuator

    HAL EXPLORER - http://localhost:8080/explorer/index.html#uri=http://localhost:8080/asset

    H2 CONSOLE - localhost:8080/h2-console

 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultaAtivoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsultaAtivoApiApplication.class, args);
    }
}