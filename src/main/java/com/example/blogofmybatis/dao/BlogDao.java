package com.example.blogofmybatis.dao;

import com.example.blogofmybatis.pojo.Blog;
import com.example.blogofmybatis.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

//动态组合查询（模糊查询）
@Repository
@Mapper
public interface BlogDao {
    //查询所有
    List<Blog> findAll(Page page);

    //查询
    Blog getBlogById(Long id);

    //添加
    int saveBlog(Blog blog);

    //修改
    int updateBlog(Blog blog);

    //删除
    void deleteBlog(Long id);

    //前台首页展示博客
    List<BlogIndex> findBlogIndex(Page page);

    //前台分类展示博客
    List<BlogIndex> getBlogByTypeId(Long id);

    //前台标签展示博客
    List<BlogIndex> getBlogByTagId(Long id);

    //获取博客发布年份
    List<String> getBlogYear();

    //获取博客发布该年份的日期
    List<Blog> getBlogByYear(String year);

    //获取博客详情
    BlogDetail getBlogDetailById(Long id);

    //修改博客观看次数
    void updateViews(Long id);

    //博客与标签表对应关系
    void blogToTags(Long blogId, Long tagId);

    //删除blog与tag的中间表数据
    void deleteBlogToTags(Long blogId, Long tagId);

    //记录博客总数
    Long count();

    //搜索博客
    List<BlogIndex> searchBlog(String query);

    //博客管理中根据查询局部刷新
    List<Blog> listBlog(SearchBlog blog);

    //根据type的分类获取type,id,个数
    List<BlogTypeWithCount> getBlogCountByTypeId();

    //根据tag的分类获取type,id,个数
    List<BlogTagWithCount> getBlogCountByTagId();

    //根据blogId查询标签
    List<Long> findBlogToTagsByBlogId(Long blogId);
}
