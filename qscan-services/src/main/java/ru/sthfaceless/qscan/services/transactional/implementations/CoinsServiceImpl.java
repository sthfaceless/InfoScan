package ru.sthfaceless.qscan.services.transactional.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.spaceouter.infoscan.dto.coins.*;
import ru.spaceouter.infoscan.dto.model.WalletModelDTO;
import ru.spaceouter.infoscan.model.CoinsCustomDAO;
import ru.spaceouter.infoscan.model.CoinsSpringDAO;
import ru.spaceouter.infoscan.model.CommonDAO;
import ru.spaceouter.infoscan.model.entities.coins.CoinsEntity;
import ru.spaceouter.infoscan.model.entities.coins.CoinsPaymentEntity;
import ru.spaceouter.infoscan.model.entities.coins.WalletEntity;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;
import ru.spaceouter.infoscan.services.BillingService;
import ru.spaceouter.infoscan.services.ParametersValidator;
import ru.spaceouter.infoscan.services.transactional.CoinsService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author danil
 * @date 20.04.19
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CoinsServiceImpl implements CoinsService {

    private final CoinsSpringDAO coinsDAO;
    private final CoinsCustomDAO coinsCustomDAO;
    private final CommonDAO commonDAO;

    private final BillingService billingService;
    private final ParametersValidator parametersValidator;

    private final int paymentsSize;

    public CoinsServiceImpl(CoinsSpringDAO coinsDAO, CoinsCustomDAO coinsCustomDAO, CommonDAO commonDAO,
                            BillingService billingService, ParametersValidator parametersValidator,
                            @Value("{coins.view.size}") Integer paymentsSize) {
        this.coinsDAO = coinsDAO;
        this.coinsCustomDAO = coinsCustomDAO;
        this.commonDAO = commonDAO;
        this.billingService = billingService;
        this.parametersValidator = parametersValidator;
        this.paymentsSize = paymentsSize;
    }

    @Transactional(readOnly = true)
    @Override
    public CoinsDTO getCoinsSize(long userId) {

        long amount = coinsDAO.userCoinsSize(commonDAO.getEntityProxy(UserEntity.class ,userId));

        return new CoinsDTO(userId, amount);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PaymentDTO> getPaymentsHistory(long userId, String start, String order, String type) {

        int startValue = 0;
        if(start != null && parametersValidator.validateNumber(start))
            startValue = Integer.parseInt(start);

        return coinsCustomDAO.getPayments(userId, startValue, this.paymentsSize, order, type);
    }

    @Override
    public void requestPayment(long userId, PaymentRequest paymentRequest) {

        String id = paymentRequest.isAnonymous() ? paymentRequest.getId()
                : this.coinsDAO.getWalletIdByMethodAndUser(paymentRequest.getMethod(), commonDAO.getEntityProxy(UserEntity.class, userId));
        if(StringUtils.isEmpty(id)) return;

        String paymentID = UUID.randomUUID().toString();
        boolean status;
        switch (paymentRequest.getMethod()){
            case QIWI:
                status = this.billingService.createQiwiRequest(paymentRequest.getId(), paymentID);break;
            case CARD:
                status = this.billingService.createCardRequest(paymentRequest.getId(), paymentID);break;
            case WEBMONEY:
                status = this.billingService.createWebmoneyRequest(paymentRequest.getId(), paymentID);break;
            default:
                status = false;
        }
        if(!status) return;

        long coinsId = this.coinsDAO.getCoinsIdByUser(this.commonDAO.getEntityProxy(UserEntity.class, userId));
        CoinsPaymentEntity paymentEntity = new CoinsPaymentEntity(
                paymentRequest.getAmount(), new Date(), paymentRequest.getMethod(),
                paymentID, PaymentStatus.REQUESTED);
        paymentEntity.setCoins(this.commonDAO.getEntityProxy(CoinsEntity.class, coinsId));

        commonDAO.persistEntity(paymentEntity);
    }

    @Override
    public void addBillingInfo(long userId, BillingInfo billingInfo) {

        List<WalletModelDTO> wallets = this.coinsDAO.findWalletsByUser(
                this.commonDAO.getEntityProxy(UserEntity.class, userId));

        WalletModelDTO wallet = null;
        for(WalletModelDTO unit: wallets)
            if(unit.getMethod() == billingInfo.getMethod()) wallet = unit;

        if(wallet != null)
            if(!wallet.getSystemId().equals(billingInfo.getId())) this.coinsDAO.updateWallet(billingInfo.getId(),
                    commonDAO.getEntityProxy(CoinsEntity.class, wallet.getCoinsId()), wallet.getMethod());
        else{
            long coinsId = wallets.size() == 0 ?
                    this.coinsDAO.getCoinsIdByUser(this.commonDAO.getEntityProxy(UserEntity.class, userId))
                    : wallets.get(0).getCoinsId();

            this.commonDAO.persistEntity(new WalletEntity(billingInfo.getId(), billingInfo.getMethod(),
                    commonDAO.getEntityProxy(CoinsEntity.class, coinsId)));
        }
    }
}
