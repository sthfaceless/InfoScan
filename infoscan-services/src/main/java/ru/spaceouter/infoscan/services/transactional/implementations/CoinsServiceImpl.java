package ru.spaceouter.infoscan.services.transactional.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.spaceouter.infoscan.dto.coins.PaymentDTO;
import ru.spaceouter.infoscan.model.CoinsCustomDAO;
import ru.spaceouter.infoscan.model.CoinsSpringDAO;
import ru.spaceouter.infoscan.model.hibernate.ProxyDAO;
import ru.spaceouter.infoscan.services.transactional.CoinsService;

import java.util.List;

/**
 * @author danil
 * @date 20.04.19
 */
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class CoinsServiceImpl implements CoinsService {

    private final CoinsSpringDAO coinsDAO;
    private final CoinsCustomDAO coinsCustomDAO;
    private final ProxyDAO proxyDAO;

    @Transactional(readOnly = true)
    @Override
    public long getCoinsSize(long userId) {

        return coinsDAO.userCoinsSize(
                proxyDAO.getUserProxy(userId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PaymentDTO> getPaymentsHistory(long userId) {

        return coinsCustomDAO.getPayments(userId);
    }

    @Override
    public void requestPayment(long userId) {
        //TODO add payment
    }
}
