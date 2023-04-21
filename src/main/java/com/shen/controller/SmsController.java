package com.shen.controller;

import com.shen.pojo.User;
import com.shen.service.SendSmsService;
import com.shen.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SmsController {
    @Resource
    SendSmsService sendSmsService;
    @Resource
    UserService userService;

//    @Scheduled(cron = "0 * * * * ?")
    public String send(){
        List<User> users = userService.selectUser();
        boolean aBoolean = new Boolean(true);
        for(User user:users){
            String phone = user.getPhone();
            aBoolean = sendSmsService.sendSms(phone);
        }
        if (aBoolean){
            return "ok";
        }{
            return "Fail";
        }
    }
}
