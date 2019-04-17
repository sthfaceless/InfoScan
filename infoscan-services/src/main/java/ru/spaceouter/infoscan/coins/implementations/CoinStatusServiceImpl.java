package ru.spaceouter.infoscan.coins.implementations;

import org.springframework.stereotype.Service;
import ru.spaceouter.infoscan.coins.CoinStatusService;
import ru.spaceouter.infoscan.coins.UserCoinStatusDTO;

/**
 * @author danil
 * @date 14.04.19
 */

@Service
public class CoinStatusServiceImpl implements CoinStatusService {

    @Override
    public UserCoinStatusDTO getCoinStatus(long userId) {

        return userId == 1 ?
                new UserCoinStatusDTO(1, 100)
                : new UserCoinStatusDTO(-1, 0);
    }
}
