package com.shen.mapper;


import com.shen.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    //选择全部用户
    List<User> selectList();
    //根据ID选择用户
    User selectUserById(int id);
    //添加一个用户
    int addUser(User user);
    //修改一个用户
    int updateUser(User user);
    //根据ID删除用户
    int deleteUser(int id);

    User selectOneByPhone(String phone);

    User selectUserByname(String user_name);

}
