package ru.sthfaceless.qscan.services.transactional;

import ru.spaceouter.infoscan.dto.coins.BillingInfo;
import ru.spaceouter.infoscan.dto.coins.CoinsDTO;
import ru.spaceouter.infoscan.dto.coins.PaymentDTO;
import ru.spaceouter.infoscan.dto.coins.PaymentRequest;

import java.util.List;

/**
 * @author danil
 * @date 20.04.19
 */
public interface CoinsService {

    CoinsDTO getCoinsSize(long userId);

    List<PaymentDTO> getPaymentsHistory(long userId, String start, String order, String type);

    void requestPayment(long userId, PaymentRequest paymentRequest);

    void addBillingInfo(long userId, BillingInfo billingInfo);

}
