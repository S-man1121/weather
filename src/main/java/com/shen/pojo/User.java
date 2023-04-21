package com.shen.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String user_name;
    private String user_password;
    private String email;
    private String phone;
    private String user_status;
    private String city;
}
