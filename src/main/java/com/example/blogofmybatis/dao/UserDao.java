package com.example.blogofmybatis.dao;

import com.example.blogofmybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    User findByUsernameAndPassword(String username,String password);
}
