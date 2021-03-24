package ru.spaceouter.infoscan.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.spaceouter.infoscan.model.entities.user.ActivationEntity;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

public interface ActivateSpringDAO extends CrudRepository<ActivationEntity, Long> {

    @Query("update ActivationEntity set confirmRestore=:uuid where activationId in " +
            "(select ae.activationId from AuthEntity a left join a.activation ae where a.email=:email)")
    @Modifying
    void setConfirmPasswordToken(@Param("uuid") String uuid, @Param("email") String email);

    @Query("update ActivationEntity set confirmEmail=:uuid, tempEmail=:email where activationId in " +
            "(select ae.activationId from AuthEntity a left join a.activation ae where a.user =:user)")
    @Modifying
    void setConfirmEmailToken(@Param("user") UserEntity user, @Param("uuid") String uuid, @Param("email") String email);

    @Query("update AuthEntity set email=:email where authId in " +
            "(select a.authId from ActivationEntity ae left join ae.auth a where ae.confirmEmail=:uuid)")
    @Modifying
    void confirmEmail(@Param("uuid") String uuid,@Param("email") String email);

    @Query("update AuthEntity set password=:pass where authId in " +
            "(select a.authId from ActivationEntity ae left join ae.auth a where ae.confirmRestore=:uuid)")
    @Modifying
    void confirmPassword(@Param("uuid") String uuid,@Param("pass") String pass);

    @Query("update AuthEntity set active=true where authId in" +
            "(select a.authId from ActivationEntity ae left join ae.auth a where ae.activateAccount=:uuid)")
    @Modifying
    void activateAccount(@Param("uuid") String uuid);

    @Query("select tempEmail from ActivationEntity where confirmEmail=:uuid")
    String getTempEmail(@Param("uuid") String uuid);
}
