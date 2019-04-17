package ru.spaceouter.infoscan.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan({
        "ru.spaceouter.infoscan"
})
@EnableWebMvc
public class InfoScanApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoScanApplication.class, args);
    }

}
