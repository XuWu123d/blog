package com.example.blogofmybatis.web;

import com.example.blogofmybatis.service.BlogService;
import com.example.blogofmybatis.vo.BlogIndex;
import com.example.blogofmybatis.vo.BlogTagWithCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/tags/{id}")
    public String tags(@PathVariable("id") Long id,Model model) {
        List<BlogTagWithCount> tags = blogService.getBlogCountByTagId();
        if (id==-1) {
            id=tags.get(0).getId();
        }
        List<BlogIndex> blogTag= blogService.getBlogByTagId(id);
        model.addAttribute("page",blogTag);
        model.addAttribute("tags",tags);
        return "tags";
    }
}
