package com.example.blogofmybatis.controller;

import com.example.blogofmybatis.pojo.Comment;
import com.example.blogofmybatis.pojo.User;
import com.example.blogofmybatis.service.BlogService;
import com.example.blogofmybatis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

//评论留言区
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Value("${comment.avatar}")
    private String avatar;

    //局部刷新
    @RequestMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model,HttpSession session) {
        //将点击博客详情后的博客id存进session域中
        session.setAttribute("blogId",blogId);
        model.addAttribute("comments",commentService.ListCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    //接收提交的信息
    @PostMapping("/comments")
    public String post(Comment comment ,HttpSession session) {
        //从session域中获取当前博客的id
        Long blogId =(Long) session.getAttribute("blogId");
        comment.setBlogId(blogId);
        comment.setBlog(blogService.getBlogConvert(blogId));
        //用完后销毁
        session.removeAttribute("blogId");
        User user =(User) session.getAttribute("user");
        //判断是否是博主
        if (user!=null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlogId();
    }

//    @PostMapping("/delete")
//    public String deleteComment(@PathVariable("id") Long id) {
//        commentService.deleteComment(id);
//        return "redirect:/comments/"
//    }
}
