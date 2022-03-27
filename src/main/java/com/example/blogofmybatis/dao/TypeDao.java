package com.example.blogofmybatis.dao;

import com.example.blogofmybatis.pojo.Type;
import com.example.blogofmybatis.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeDao {
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

    //查类型总数
    Long countType();
}
