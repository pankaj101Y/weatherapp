package com.example.weatherapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastResponseDto {
    private String source;
    private Double low;
    private Double high;
    private List<TemperatureDto> temperatures;
}
