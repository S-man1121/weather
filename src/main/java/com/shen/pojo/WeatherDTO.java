package com.shen.pojo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WeatherDTO {
    private String status;
    private String count;
    private String info;
    private String infocode;
    private ArrayList<Forecast> forecasts;
}


