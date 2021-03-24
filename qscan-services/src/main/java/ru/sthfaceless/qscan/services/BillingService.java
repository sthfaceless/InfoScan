package ru.sthfaceless.qscan.services;

public interface BillingService {

    boolean createQiwiRequest(String systemId, String paymentId);

    boolean createCardRequest(String systemId, String paymentId);

    boolean createWebmoneyRequest(String systemId, String paymentId);

}
