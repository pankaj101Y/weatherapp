package com.example.weatherapp.controller;

import com.example.weatherapp.request.GetForecastRequestDto;
import com.example.weatherapp.response.ForecastResponseDto;
import com.example.weatherapp.response.GenericResponse;
import com.example.weatherapp.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast")
    @ApiOperation("Get weather forecast")
    public GenericResponse<ForecastResponseDto> getForecast(@ModelAttribute @Valid GetForecastRequestDto forecastDto) {
        return new GenericResponse<>(weatherService.getForecast(forecastDto));
    }
}
