package com.example.weatherapp.service.impl;

import com.example.weatherapp.request.GetForecastRequestDto;
import com.example.weatherapp.response.ForecastResponseDto;
import com.example.weatherapp.response.TemperatureDto;
import com.example.weatherapp.service.WeatherClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockedWeatherClient implements WeatherClient {

    @Override
    public ForecastResponseDto getForecast(GetForecastRequestDto forecastDto) {
        ForecastResponseDto forecastResponse = new ForecastResponseDto();

        double max=0.0,min=0.0;
        long current=forecastDto.getStartTime();
        List<TemperatureDto> temperatures = new ArrayList<>();


        while (current <= forecastDto.getEndTime()) {
            double temperature = -100.0f + 200.0f * Math.random();
            min = Math.min(min, temperature);
            max = Math.max(min, temperature);
            temperatures.add(new TemperatureDto(current, temperature));
            current += forecastDto.getTimeIntervalInSeconds();
        }

        forecastResponse.setLow(min);
        forecastResponse.setHigh(max);
        forecastResponse.setTemperatures(temperatures);

        return forecastResponse;
    }
}
