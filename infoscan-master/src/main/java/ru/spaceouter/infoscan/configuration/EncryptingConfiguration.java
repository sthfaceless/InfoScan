package ru.spaceouter.infoscan.configuration;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author danil
 * @date 21.04.19
 */
@Configuration
@PropertySource("classpath:security.properties")
public class EncryptingConfiguration {

    @Setter
    @Value("${security.encrypt.power}")
    private Integer integer;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder(integer);
    }
}
