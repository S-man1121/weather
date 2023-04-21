package com.shen.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;


public class JsonUtils {

    public static String getJson(Object obj){
        return getJson(obj,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getJson(Object obj,String dateFormat){
        ObjectMapper mapper =new ObjectMapper();
        //关闭原来mapper的时间戳格式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //修改成输入的时间格式
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(dateFormat);

        mapper.setDateFormat(simpleDateFormat);

        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
