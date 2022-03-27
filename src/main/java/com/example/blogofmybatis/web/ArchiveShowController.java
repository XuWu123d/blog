package com.example.blogofmybatis.web;

import com.example.blogofmybatis.pojo.Blog;
import com.example.blogofmybatis.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;

@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/archives")
    public String archive(Model model) {
        Map<String, List<Blog>> archiveBlog = blogService.getArchiveBlog();
        Long count = blogService.countBlog();
        model.addAttribute("count",count);
        model.addAttribute("archiveMap",archiveBlog);
        return "archives";
    }
}
