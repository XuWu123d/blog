package com.example.blogofmybatis.web;

import com.example.blogofmybatis.service.BlogService;
import com.example.blogofmybatis.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    //前端页面展示
    @RequestMapping("/")
    public String index(@RequestParam(value = "pageNum",defaultValue = "1") Long pageNum, Model model) {
        Page page=new Page();
        page.setEnd(3L);   //设置一页博客数量
        page.setPageNum(pageNum);
        Page p = blogService.page(page);
        List<BlogIndex> all = blogService.findBlogIndex(p);
        model.addAttribute("page",all);  //博客信息
        model.addAttribute("pages",p); //页码信息
        List<BlogTypeWithCount> types= blogService.getBlogCountByTypeId();
        model.addAttribute("types",types);  //类型信息
        List<BlogTagWithCount> tags = blogService.getBlogCountByTagId();
        model.addAttribute("tags",tags);   //标签信息
        return "index";
    }

    //查看博客详情
    @RequestMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id,Model model) {
        BlogDetail blog = blogService.getBlogConvert(id);
        System.out.println(blog);
        model.addAttribute("blog",blog);
        return "blog";
    }

    //搜索博客
    @PostMapping("/search")
    public String search(@RequestParam String query,Model model) {
        List<BlogIndex> blog = blogService.searchBlog(query);
        int count = blog.size();
        model.addAttribute("page",blog);
        model.addAttribute("query",query);
        model.addAttribute("count",count);
        return "search";
    }

    //动态获取底部用户信息
//    @RequestMapping("/footer/newblog")
//    public String newblog(Model model) {
//        List<Blog> blogs = blogService.listBlog();
//        model.addAttribute("newblogs",blogs);
//        return "_fragments::newblogList";
//    }
}
