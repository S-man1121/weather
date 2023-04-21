package com.shen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController{

    @GetMapping("/")
    public String inidex(){
        return "index";
    }

    @GetMapping("/weather")
    public String weather(){
        return "weather";
    }
}

