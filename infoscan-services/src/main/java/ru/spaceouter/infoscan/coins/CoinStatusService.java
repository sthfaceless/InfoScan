package ru.spaceouter.infoscan.coins;

/**
 * @author danil
 * @date 14.04.19
 */
public interface CoinStatusService {

    UserCoinStatusDTO getCoinStatus(long userId);

}
