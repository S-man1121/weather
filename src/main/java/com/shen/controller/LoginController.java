package com.shen.controller;


import com.shen.mapper.UserMapper;
import com.shen.pojo.User;
import com.shen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(String username , String password, Model model, HttpSession httpSession){
        if(!ObjectUtils.isEmpty(username) && !ObjectUtils.isEmpty(password)){
            User user = userService.selectUserByname(username);
            if(user!=null){
                if(password.equals(user.getUser_password())){
                    httpSession.setAttribute("loginUser",username);
                    return "redirect:/index.html";
                }else{
                    model.addAttribute("msg","账号或密码错误，请重试");
                }
            }else{
                model.addAttribute("msg","账号或密码错误，请重试");
            }
        }
        return "login";
    }
}
