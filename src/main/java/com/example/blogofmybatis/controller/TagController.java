package com.example.blogofmybatis.controller;

import com.example.blogofmybatis.pojo.Tag;
import com.example.blogofmybatis.service.TagService;
import com.example.blogofmybatis.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/tag")
    public String tag(@RequestParam(value = "pageNum",defaultValue = "1") Long pageNum, Model model) {
        Page page=new Page();
        page.setPageNum(pageNum);
        page.setEnd(5L);
        Page page1 = tagService.page(page);
        List<Tag> tags = tagService.pageTag(page1);
        model.addAttribute("tags",tags);
        model.addAttribute("pages",page1);
        return "/admin/tags";
    }

    //校验编辑
    @PostMapping("/tagInput/{id}")
    public String checkUpdate(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName!=null) {
            result.rejectValue("name","nameError","不能添加重复标签");
        }
        if (result.hasErrors()) {
            return "admin/tag-input";
        }
        int tag1 = tagService.updateTag(tag);
        if (tag1==0) {
            attributes.addFlashAttribute("message","更新失败");
        } else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tag";
    }

    //编辑
    @RequestMapping("/tag/{id}/input")
    public String update(@PathVariable Long id,Model model)  {
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tag-input";
    }

    //校验新增
    @PostMapping("/tagInput")
    public  String checkSave(@Valid Tag tag,BindingResult result,RedirectAttributes attributes) {
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName!=null) {
            result.rejectValue("name","nameError","不能添加重复标签");
        }
        if (result.hasErrors()) {
            return "admin/tag-input";
        }
        int tag1 = tagService.saveTag(tag);
        if (tag1==0) {
            attributes.addFlashAttribute("message","添加失败");
        } else {
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/tag";
    }

    //新增
    @RequestMapping("/tag/input")
    public String save(Model model) {
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }

    //删除
    @RequestMapping("/tag/{id}/delete")
    public String delete(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tag";
    }
}
