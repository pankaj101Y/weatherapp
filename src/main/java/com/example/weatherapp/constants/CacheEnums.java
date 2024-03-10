package com.example.weatherapp.constants;

import java.time.Duration;

public enum CacheEnums {
    FORECAST_CACHE("forecast_cache",Duration.ofMinutes(30),Duration.ofMinutes(30));

    public final Duration expirationTime;
    public final Duration refreshTime;

    public final String name;
    public final int maxSize;
    public static final int MAX_SIZE=100;




    CacheEnums(String name, Duration exp, Duration refresh) {
        this.name=name;
        this.expirationTime=exp;
        this.refreshTime=refresh;
        this.maxSize=MAX_SIZE;
    }

}