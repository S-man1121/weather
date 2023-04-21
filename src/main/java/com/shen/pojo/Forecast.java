package com.shen.pojo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Forecast {
    private String city;
    private String adcode;
    private String province;
    private String reporttime;
    private ArrayList<Cast> casts;
}
