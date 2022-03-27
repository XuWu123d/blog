package com.example.blogofmybatis.vo;

/**
 * 页码
 */
public class Page {
    private Long pageNum;    //当前页
    private Long start;      //开始索引
    private Long end;        //一页的数量
    private Long totalPage;  //总共页数
    private Long count;  //总数

    public Page() {
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", start=" + start +
                ", end=" + end +
                ", totalPage=" + totalPage +
                ", count=" + count +
                '}';
    }
}
