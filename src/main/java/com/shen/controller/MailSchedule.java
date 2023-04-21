package com.shen.controller;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MailSchedule {


    @Resource
    JavaMailSenderImpl mailSender;
    @Resource
    Weathercontroller weathercontroller;


////    @Scheduled(cron = "0 * * * * ?")
//    public void contextLoads(){
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        String weather = weathercontroller.getWeather();
//        mailMessage.setSubject("尊贵的申钊锋先生");
//        mailMessage.setText(weather);
//        mailMessage.setTo("2221225104@qq.com");
//        mailMessage.setFrom("1317063939@qq.com");
//        mailSender.send(mailMessage);
//    }
}
