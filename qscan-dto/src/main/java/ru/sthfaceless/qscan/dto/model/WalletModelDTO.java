package ru.sthfaceless.qscan.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.spaceouter.infoscan.dto.coins.BillingMethod;

@Getter
@AllArgsConstructor
public class WalletModelDTO {

    private long coinsId;
    private String systemId;
    private BillingMethod method;

}
