package com.example.blogofmybatis.pojo;

import com.example.blogofmybatis.vo.BlogDetail;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
    private Long id; //id
    private String nickname; //昵称
    private String email; //邮箱
    private String content; //评论内容
    private String avatar; //头像

    private Date createTime; //创建时间
    private Long blogId;
    private BlogDetail blog; //与blog表对应字段

    private List<Comment> comments=new ArrayList<>(); //评论区回复中的子（回复者）

    private Long commentId;
    private String commentNickname;
    private Comment comment; //评论区回复中的父（被回复者）
    private boolean adminComment; //是否为管理员

    public Comment() {

    }

    public Comment(Long id, String nickname, String email, String content, String avatar, Date createTime) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.avatar = avatar;
        this.createTime = createTime;
    }

    public BlogDetail getBlog() {
        return blog;
    }

    public void setBlog(BlogDetail blog) {
        this.blog = blog;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentNickname() {
        return commentNickname;
    }

    public void setCommentNickname(String commentNickname) {
        this.commentNickname = commentNickname;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blogId=" + blogId +
                ", blog=" + blog +
                ", comments=" + comments +
                ", commentId=" + commentId +
                ", commentNickname='" + commentNickname + '\'' +
                ", comment=" + comment +
                ", adminComment=" + adminComment +
                '}';
    }
}
