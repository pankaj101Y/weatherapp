package com.example.weatherapp.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GenericResponse<T> {
    private String errorMessage;
    private T data;

    public GenericResponse(T d){
        this.data=d;
    }
}
