package ru.spaceouter.infoscan.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
* Temporary exclude auto configs
* */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, IntegrationAutoConfiguration.class})
@ComponentScan({
        "ru.spaceouter.infoscan"
})
@EnableWebMvc
public class InfoScanApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoScanApplication.class, args);
    }

}
