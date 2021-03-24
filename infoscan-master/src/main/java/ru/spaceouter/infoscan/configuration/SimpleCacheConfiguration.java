package ru.spaceouter.infoscan.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

/**
 * @author danil
 * @date 21.04.19
 */
@Configuration
@EnableCaching
public class SimpleCacheConfiguration {

    public static final String AUTHENTICATION_CACHE = "authentications";

    @Bean
    CacheManager cacheManager(){

        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(Arrays.asList(
                "authentications"
        ));

        return cacheManager;
    }

    @CacheEvict(cacheNames = {AUTHENTICATION_CACHE}, allEntries = true)
    @Scheduled(fixedDelay = 24 * 60 * 60 * 10001)
    public void clearCache(){

    }

}
