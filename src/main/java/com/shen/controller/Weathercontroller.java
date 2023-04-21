package com.shen.controller;

import com.alibaba.fastjson.JSON;
import com.shen.pojo.Cast;
import com.shen.pojo.Forecast;
import com.shen.pojo.User;
import com.shen.pojo.WeatherDTO;
import com.shen.service.UserService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class Weathercontroller {
    private static final OkHttpClient client = new OkHttpClient();
    @Resource
    UserService userService;



    public WeatherDTO selectWeacher(String city) throws IOException {
        String url1 = "https://restapi.amap.com/v3/weather/weatherInfo?key=fbcde8485aeff32abb3e50e186dc589b&city="+city+"&extensions=all";
        Request request = new Request.Builder().url(url1).build();
        Response execute = client.newCall(request).execute();
        String res= execute.body().string();
        return JSON.parseObject(res,WeatherDTO.class);
    }
    public String getWeather(String phone){
        try {
            User user = userService.selectOneByPhone(phone);
            WeatherDTO weatherDTO = selectWeacher(user.getCity());
            ArrayList<Forecast> forecasts = weatherDTO.getForecasts();
            Forecast weath = forecasts.get(0);
            ArrayList<Cast> casts = weath.getCasts();
            String content="地区:"+weath.getCity()+"       更新时间："+weath.getReporttime()+"\n";
            Cast cast = casts.get(0);
            content += "今日天气：" + cast.getDayweather() + "；\n气温：" + cast.getNighttemp() + "~" + cast.getDaytemp() + "℃；\n风力：" + cast.getDaypower() + "级" + cast.getDaywind() + "风。";
            return content;
        } catch (IOException e) {
            return "天气获取错误，请您联系SZF：1317063939@qq.com反应情况";
        }
    }

}
