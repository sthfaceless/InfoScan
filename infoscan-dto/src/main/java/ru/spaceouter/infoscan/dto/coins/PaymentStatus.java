package ru.spaceouter.infoscan.dto.coins;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentStatus {
    REQUESTED("REQUESTED"), SUCCESS("SUCCESS") ,FAILED("FAILED");
    private final String s;
    @Override
    public String toString() {
        return s;
    }
}
