package com.example.blogofmybatis.dao;

import com.example.blogofmybatis.pojo.Tag;
import com.example.blogofmybatis.vo.BlogIndex;
import com.example.blogofmybatis.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagDao {

    //添加
    int saveTag(Tag tag);

    //根据id获取
    Tag getTag(Long id);

    //根据name获取
    Tag getTagByName(String name);

    //查询所有
    List<Tag> listTag();

    //分页查询
    List<Tag> pageTag(Page page);

    //修改
    int updateTag(Tag tag);

    //删除
    void deleteTag(Long id);

    //+++++++++++++++++++++++++++
    //根据tag的多个id查出对应的多个tag
    List<Tag> findAllById(List<Long> tagIds);

    //根据标签查询博客
    List<BlogIndex> getBlogByTagId(Long id);

    //查询数量
    Long count();

//    //添加tag与blog表关系
//    void tagToBlogs(Long tagId, Long blogId);
//
//    //删除tag与blog表关系
//    void deleteTagToBlogs(Long tagId, Long blogId);
}
