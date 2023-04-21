package com.shen.service;

import com.shen.mapper.UserMapper;
import com.shen.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    //查询所有的用户
    public List<User> selectUser(){
        return userMapper.selectList();
    }

    public User selectUserById(int id){
        User user = userMapper.selectUserById(id);
        return user;
    }

    public void addUser(User user){
        userMapper.addUser(user);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public void deleteUser(int id){
        userMapper.deleteUser(id);
    }

    public User selectOneByPhone(String phone){
        return userMapper.selectOneByPhone(phone);
    }

    public User selectUserByname(String name){
        return userMapper.selectUserByname(name);
    }

}
