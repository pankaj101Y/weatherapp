package com.example.weatherapp.service;

import com.example.weatherapp.request.GetForecastRequestDto;
import com.example.weatherapp.response.ForecastResponseDto;

public interface WeatherClient {
    ForecastResponseDto getForecast(GetForecastRequestDto forecastDto);
}
