package com.example.blogofmybatis.vo;

/**
 * 博客标签及数量的实体类
 */
public class BlogTypeWithCount {
    private Long id;
    private String name;
    private Long totalByType;

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

    public Long getTotalByType() {
        return totalByType;
    }

    public void setTotalByType(Long totalByType) {
        this.totalByType = totalByType;
    }

    @Override
    public String toString() {
        return "BlogTypeWithCount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalByType=" + totalByType +
                '}';
    }
}
