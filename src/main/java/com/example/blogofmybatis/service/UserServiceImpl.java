package com.example.blogofmybatis.service;

import com.example.blogofmybatis.dao.UserDao;
import com.example.blogofmybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        User user= userDao.findByUsernameAndPassword(username, password);
        return user;
    }
}
