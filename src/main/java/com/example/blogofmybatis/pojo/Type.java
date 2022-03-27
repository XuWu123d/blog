package com.example.blogofmybatis.pojo;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Type {
    private Long id; //id
    @NotBlank(message = "分类名称不能为空") //校验
    private String name; //姓名
    private List<Blog> blogs=new ArrayList<>(); //与blog表对应字段

    public Type() {

    }

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
