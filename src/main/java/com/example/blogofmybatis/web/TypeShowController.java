package com.example.blogofmybatis.web;

import com.example.blogofmybatis.service.BlogService;
import com.example.blogofmybatis.service.TypeService;
import com.example.blogofmybatis.vo.BlogIndex;
import com.example.blogofmybatis.vo.BlogTypeWithCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//标签页面展示
@Controller
public class TypeShowController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/type/{id}")
    public String types(@PathVariable("id") Long id,Model model) {
        List<BlogTypeWithCount> types = blogService.getBlogCountByTypeId();
        if (id == -1) {
            id=types.get(0).getId();
        }
        List<BlogIndex> blogById = blogService.getBlogByTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogById);
        return "types";
    }

}
