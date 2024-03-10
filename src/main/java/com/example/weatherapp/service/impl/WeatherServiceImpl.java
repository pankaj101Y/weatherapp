package com.example.weatherapp.service.impl;

import com.example.weatherapp.exception.AppException;
import com.example.weatherapp.request.GetForecastRequestDto;
import com.example.weatherapp.response.ForecastResponseDto;
import com.example.weatherapp.service.WeatherClient;
import com.example.weatherapp.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static com.example.weatherapp.constants.Beans.FORECAST_CACHE;
import static com.example.weatherapp.constants.Constants.*;
import static com.example.weatherapp.exception.ExceptionEnum.INVALID_TIME_INTERVAL;
import static com.example.weatherapp.exception.ExceptionEnum.INVALID_TIME_RANGE;

@Component
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    @Qualifier(FORECAST_CACHE)
    private Cache forecastCache;


    @Override
    public ForecastResponseDto getForecast(GetForecastRequestDto forecastDto) {
        // Here we would typically make calls to an external client weather API
        // to get the forecast data
        // For demonstration purposes, let's use MockedWeatherClient
        validate(forecastDto);
        return getFromCache(forecastDto).orElseGet(() -> getForecastFromClient(forecastDto));
    }

    private void validate(GetForecastRequestDto forecastDto) {
        if (forecastDto.getStartTime()>forecastDto.getEndTime()){
            throw new AppException(INVALID_TIME_RANGE);
        }

        if (Objects.isNull(forecastDto.getTimeIntervalInSeconds()) || forecastDto.getTimeIntervalInSeconds()< HOUR_IN_SECONDS){
            throw new AppException(INVALID_TIME_INTERVAL);
        }
    }

    private ForecastResponseDto getForecastFromClient(GetForecastRequestDto forecastDto) {
        ForecastResponseDto forecastResponseDto = weatherClient.getForecast(forecastDto);
        forecastCache.put(forecastDto, forecastResponseDto);
        forecastResponseDto.setSource(API);
        return forecastResponseDto;
    }

    private Optional<ForecastResponseDto> getFromCache(GetForecastRequestDto forecastRequestDto) {
        try {
            Cache.ValueWrapper valueWrapper = forecastCache.get(forecastRequestDto);
            ForecastResponseDto forecastResponseDto = (ForecastResponseDto) valueWrapper.get();
            if (Objects.nonNull(forecastResponseDto)) {
                forecastResponseDto.setSource(CACHE);
                return Optional.of(forecastResponseDto);
            }
        } catch (Exception e) {
            log.error("error while getFromCache key {} ", forecastRequestDto, e);
        }
        return Optional.empty();
    }
}
