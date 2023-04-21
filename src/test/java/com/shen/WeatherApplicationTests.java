package com.shen;

import com.shen.controller.Weathercontroller;
import com.shen.pojo.WeatherDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class WeatherApplicationTests {
    @Resource
    Weathercontroller weathercontroller;
    @Test
    void contextLoads() throws IOException {
//        String weatherDTO = weathercontroller.getWeather();
//        System.out.println(weatherDTO);
    }


}
