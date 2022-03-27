package com.example.blogofmybatis.controller;

import com.example.blogofmybatis.pojo.Type;
import com.example.blogofmybatis.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    //跳转至type页面
    @RequestMapping("/type")
    public String type(@RequestParam(value = "pageNum",defaultValue = "1") Long pageNum, Model model) {
        Page page=new Page();
        page.setPageNum(pageNum);
        page.setEnd(5L);
        Page page1 = typeService.page(page);
        List<Type> types = typeService.getListType(page1);
        model.addAttribute("page",types);
        model.addAttribute("pages",page1);
        return "admin/types";
    }

    //跳转至添加页面
    @RequestMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    //校验新增模块
    @PostMapping("/input") //@Valid 要校验type对象,BindingResult为校验后的结果
    public String types_input(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type name = typeService.getTypeByName(type.getName());
        if (name!=null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        int type1 = typeService.saveType(type);
        if (type1==0) {
            //从分类页面拿到message
            attributes.addFlashAttribute("message","添加失败");
        } else {
            attributes.addFlashAttribute("message","添加成功");
        }

        return "redirect:/admin/type";
    }

    //跳转至编辑页面
    @RequestMapping("/types/{id}/input")
    public String update(@PathVariable Long id,Model model) {
        Type type = typeService.getType(id);
        model.addAttribute("type",type);
        return "admin/type-input";
    }

    //校验编辑(修改)信息
    @PostMapping("/input/{id}") //@Valid 要校验type对象,BindingResult为校验后的结果
    public String types_input1(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type name = typeService.getTypeByName(type.getName());
        if (name!=null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        int type1 = typeService.updateType(type);
        if (type1==0) {
            //从分类页面拿到message
            attributes.addFlashAttribute("message","更新失败");
        } else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/type";
    }

    //删除
    @RequestMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id) {
        typeService.deleteType(id);
        return "redirect:/admin/type";
    }
}
