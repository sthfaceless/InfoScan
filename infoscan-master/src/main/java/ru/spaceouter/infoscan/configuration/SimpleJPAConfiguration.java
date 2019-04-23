package ru.spaceouter.infoscan.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author danil
 * @date 20.04.19
 */
@Configuration
@EntityScan(basePackages = "ru.spaceouter.infoscan.model")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.spaceouter.infoscan.model")
public class SimpleJPAConfiguration {

}
