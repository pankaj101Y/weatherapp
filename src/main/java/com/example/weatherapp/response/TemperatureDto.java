package com.example.weatherapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureDto {
    private long time;
    private Double temperature;
}
