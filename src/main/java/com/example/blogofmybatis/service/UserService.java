package com.example.blogofmybatis.service;

import com.example.blogofmybatis.pojo.User;

public interface UserService {
    User checkUser(String username,String password);
}
