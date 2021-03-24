package ru.spaceouter.infoscan.dto.coins;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BillingMethod {

    QIWI("QIWI"),
    CARD("CARD"),
    WEBMONEY("WEBMONEY");

    private final String s;

    @Override
    public String toString(){
        return this.s;
    }
}
