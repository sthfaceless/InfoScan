package ru.spaceouter.infoscan.services.transactional.implementations;

import org.springframework.stereotype.Service;
import ru.spaceouter.infoscan.services.BillingService;

@Service
public class BillingServiceImpl implements BillingService {
    @Override
    public boolean createQiwiRequest(String systemId, String paymentId) {
        return false;
    }

    @Override
    public boolean createCardRequest(String systemId, String paymentId) {
        return false;
    }

    @Override
    public boolean createWebmoneyRequest(String systemId, String paymentId) {
        return false;
    }

}
