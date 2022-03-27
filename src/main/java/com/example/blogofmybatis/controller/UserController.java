package com.example.blogofmybatis.controller;

import com.example.blogofmybatis.pojo.User;
import com.example.blogofmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping
    public String loginPage() {
        return "admin/login";
    }

    //登录
    @RequestMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes,
                        Model model) {
        User user= service.checkUser(username,password);
        if (user!=null) {
            user.setPassword(null);  //安全起见，密码置空
            session.setAttribute("user",user);
            model.addAttribute("us",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    //注销
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
