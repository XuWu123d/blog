package com.example.blogofmybatis.service;

import com.example.blogofmybatis.pojo.Type;
import com.example.blogofmybatis.vo.Page;
import java.util.List;

public interface TypeService {
    //保存
    int saveType(Type type);

    //查询
    Type getType(Long id);

    //根据name查询
    Type getTypeByName(String name);


    //查询所有
    List<Type> listType();

    //分页查询
    List<Type> getListType(Page page);

    //修改
    int updateType(Type type);

    //删除
    void deleteType(Long id);

    //类型分页功能
    Page page(Page page);
}
