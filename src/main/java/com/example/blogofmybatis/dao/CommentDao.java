package com.example.blogofmybatis.dao;

import com.example.blogofmybatis.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao  {
    //获取父级评论
    List<Comment> ListCommentByBlogParentId(Long id);

    //保存评论
    int saveComment(Comment comment);

    //通过id获取到评论
    Comment commentByBlogId(Long id);

    //根据博客id和父评论获取子评论
    List<Comment> ListCommentByBlogParentNotNullId(Long blogId,Long id);

//    //删除评论
//    void deleteComment(Long id);
}
