package com.example.blogofmybatis.vo;


/**
 * 标签与博客多对多查询中间表实体类（还没有实现）
 */
public class BlogTagWithCount {
    private Long id; //id
    private String name; //姓名
    private Long blogOfTagCount;

    public BlogTagWithCount() {
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

    public Long getBlogOfTagCount() {
        return blogOfTagCount;
    }

    public void setBlogOfTagCount(Long blogOfTagCount) {
        this.blogOfTagCount = blogOfTagCount;
    }

    @Override
    public String toString() {
        return "BlogTagWithCount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogOfTagCount=" + blogOfTagCount +
                '}';
    }
}
