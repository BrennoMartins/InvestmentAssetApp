package com.app.financial.utils;

public class ExampleLooping {

    public void retornaMaiorIdade(int idade){

        String isMaior = "Não";
        switch (idade) {
            case 18 -> isMaior = "Sim";
        }
        System.out.println(isMaior);
    }
}
