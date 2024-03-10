
package com.example.weatherapp.service;

import com.example.weatherapp.request.GetForecastRequestDto;
import com.example.weatherapp.response.ForecastResponseDto;

public interface WeatherService {

    ForecastResponseDto getForecast(GetForecastRequestDto forecastDto);

}
