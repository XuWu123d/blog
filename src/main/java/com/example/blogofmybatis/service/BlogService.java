package com.example.blogofmybatis.service;

import com.example.blogofmybatis.pojo.Blog;
import com.example.blogofmybatis.vo.*;

import java.util.List;
import java.util.Map;

public interface BlogService {
    //查询所有博客
    List<Blog> findAll(Page page);

    //查询
    Blog getBlogById(Long id);

    //查询归档博客条数
    Long countBlog();

    //添加
    int saveBlog(Blog blog);

    //修改
    int updateBlog(Blog blog);

    //删除
    void deleteBlog(Long id);

    //获取页码
    Page page(Page page);

    //前台首页分页展示博客
    List<BlogIndex> findBlogIndex(Page page);

    //前台分类展示博客
    List<BlogIndex> getBlogByTypeId(Long id);

    //前台标签展示博客
    List<BlogIndex> getBlogByTagId(Long id);

    //获取归档(键为年，值为几月几日)
    Map<String,List<Blog>> getArchiveBlog();

    //获取博客详情
    BlogDetail getBlogConvert(Long id);

    //搜索博客
    List<BlogIndex> searchBlog(String query);

    //博客管理中根据查询局部刷新
    List<Blog> listBlog(SearchBlog blog);

    //根据type的分类获取type,id,个数
    List<BlogTypeWithCount> getBlogCountByTypeId();

    //根据tag的分类获取type,id,个数
    List<BlogTagWithCount> getBlogCountByTagId();
}
