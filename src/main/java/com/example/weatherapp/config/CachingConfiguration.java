package com.example.weatherapp.config;

import com.example.weatherapp.constants.CacheEnums;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.Executors;

import static com.example.weatherapp.constants.Beans.FORECAST_CACHE;
import static com.github.benmanes.caffeine.cache.Caffeine.newBuilder;

@Configuration
@EnableCaching
public class CachingConfiguration {

    @Bean
    public CacheManager cacheManager(Ticker ticker) {
        CaffeineCache forecastCache = buildCache(CacheEnums.FORECAST_CACHE.name, ticker, CacheEnums.FORECAST_CACHE.expirationTime);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(forecastCache));

        return manager;
    }

    private CaffeineCache buildCache(String name, Ticker ticker, Duration duration) {
        return new CaffeineCache(name, newBuilder()
                .expireAfterWrite(duration)
                .maximumSize(500_000)
                .executor(Executors.newCachedThreadPool())
                .ticker(ticker)
                .build());
    }

    @Bean(name = FORECAST_CACHE)
    public Cache forecastCache(CacheManager manager){
        return manager.getCache(CacheEnums.FORECAST_CACHE.name);
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }
}