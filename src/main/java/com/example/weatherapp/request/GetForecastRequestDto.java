package com.example.weatherapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class GetForecastRequestDto {

    @NotNull(message = "Please provide valid zipcode")
    private String zipCode;

    @NotNull(message = "Please provide valid start time")
    private Long startTime;

    @NotNull(message = "Please provide valid end time")
    private Long endTime;

    private Integer timeIntervalInSeconds;
}
