package ru.sthfaceless.qscan.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.spaceouter.infoscan.dto.coins.BillingMethod;
import ru.spaceouter.infoscan.dto.model.WalletModelDTO;
import ru.spaceouter.infoscan.model.entities.coins.CoinsEntity;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

import java.util.List;

/**
 * @author danil
 * @date 21.04.19
 */
@Repository
public interface CoinsSpringDAO extends CrudRepository<CoinsEntity, Long> {

    @Query("select num from CoinsEntity where user = :user")
    long userCoinsSize(@Param("user") UserEntity userEntity);

    @Query("select new ru.spaceouter.infoscan.dto.model.WalletModelDTO(c.coinsId, w.systemId, w.method) from WalletEntity w " +
            "left join w.coins c " +
            "where c.user = :user")
    List<WalletModelDTO> findWalletsByUser(@Param("user") UserEntity user);

    @Query("select w.systemId from WalletEntity w left join w.coins c where c.user=:user and w.method=:method")
    String getWalletIdByMethodAndUser(@Param("method") BillingMethod method, @Param("user") UserEntity user);

    @Query("select c.coinsId from CoinsEntity c where c.user=:user")
    long getCoinsIdByUser(@Param("user") UserEntity user);

    @Query("update WalletEntity w set w.systemId=:id where w.coins=:coins and w.method=:method")
    @Modifying
    void updateWallet(@Param("id") String systemId, @Param("coins") CoinsEntity coinsEntity, @Param("method") BillingMethod billingMethod);
}
