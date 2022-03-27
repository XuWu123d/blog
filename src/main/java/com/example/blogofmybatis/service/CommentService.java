package com.example.blogofmybatis.service;

import com.example.blogofmybatis.pojo.Comment;

import java.util.List;

public interface CommentService {
    //列出博客的评论
    List<Comment> ListCommentByBlogId(Long id);

    //保存评论
    int saveComment(Comment comment);

//    //删除评论
//    void deleteComment(Long id);
}
