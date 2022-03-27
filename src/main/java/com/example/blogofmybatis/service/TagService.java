package com.example.blogofmybatis.service;

import com.example.blogofmybatis.pojo.Tag;
import com.example.blogofmybatis.vo.BlogIndex;
import com.example.blogofmybatis.vo.Page;

import java.util.List;

public interface TagService {
    //添加
    int saveTag(Tag tag);

    //根据id获取
    Tag getTag(Long id);

    //根据name获取
    Tag getTagByName(String name);

    //分页查询
    List<Tag> pageTag(Page page);

    //查询所有
    List<Tag> listTag();

    //分页功能
    Page page(Page page);

    //传递多个id
    List<Tag> listTag(String ids);

    //修改
    int updateTag(Tag tag);

    //删除
    void deleteTag(Long id);

    //根据标签查询博客
    List<BlogIndex> getBlogByTagId(Long id);

}
