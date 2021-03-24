package ru.sthfaceless.qscan.model;

import ru.spaceouter.infoscan.dto.coins.PaymentDTO;

import java.util.List;

/**
 * @author danil
 * @date 22.04.19
 */
public interface CoinsCustomDAO {

    List<PaymentDTO> getPayments(long userId, int start, int size, String order, String type);

}
