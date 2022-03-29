package com.example.blogofmybatis.controller;

import com.example.blogofmybatis.pojo.Blog;
import com.example.blogofmybatis.pojo.Tag;
import com.example.blogofmybatis.pojo.Type;
import com.example.blogofmybatis.pojo.User;
import com.example.blogofmybatis.service.BlogService;
import com.example.blogofmybatis.service.TagService;
import com.example.blogofmybatis.service.TypeService;
import com.example.blogofmybatis.vo.Page;
import com.example.blogofmybatis.vo.SearchBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    //博客页面
    @GetMapping("/blogs")
    public String blogs(@RequestParam(value = "pageNum",defaultValue = "1") Long pageNum, Model model) {
        Page page=new Page();
        page.setPageNum(pageNum);  //设置当前页
        page.setEnd(5L);        //设置一页数量
        Page page1 = blogService.page(page);
        List<Type> types = typeService.listType();
        List<Blog> blogs = blogService.findAll(page1);
        model.addAttribute("types",types);
        model.addAttribute("page",blogs);
        model.addAttribute("pages",page1);
        return "admin/blogs";
    }

    //搜索刷新
    @RequestMapping("/blogs/search")
    public String search(SearchBlog blog, Model model) {
        List<Blog> blogs=blogService.listBlog(blog);
        model.addAttribute("page",blogs);
        return "admin/blogs :: blogList";
    }

    //跳转编辑页面
    @RequestMapping("/blogs/{id}/input")
    public String edit(@PathVariable("id") Long id,Model model) {
        List<Type> types = typeService.listType();
        List<Tag> tags = tagService.listTag();
        Blog blogById = blogService.getBlogById(id);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("blog",blogById);
        return "admin/blogs-input";
    }

    /*由于建表语句之前是jpa自动生成，主键不能为空且不能自增长（除博客表，被我改过），导致博客管理界面无法编辑(修改提交后报错)，
    而分类和标签界面无法新增（原因就是主键不能为空且不能自增长，导致id为空报错）
    将建表语句改为主键自增后分类和标签界面正常，博客管理界面无论是新增还是编辑发布状态都是草稿（先放一放）*/
    //编辑(修改)完后进行保存
    @PostMapping("/blog/{id}")
    public String save(@Valid Blog blog) {
        blogService.updateBlog(blog);
        return "redirect:/admin/blogs";
    }

    //删除
    @RequestMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }

    //新增
    @GetMapping("/blogs/input")
    public String input(Model model) {
        List<Type> types = typeService.listType();
        List<Tag> tags = tagService.listTag();
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    //新增点击发布后对数据进行保存
    //这里踩了个大坑
    @PostMapping("/blog")
    public String post(Blog blog,HttpSession session,RedirectAttributes attributes) {
//        System.out.println(blog);
        //Blog{id=null, title='维护', content='页面维护', firstPicture='/image/3.jpg', flag='', views=null, appreciation=true, shareStatement=true, commentTabled=true, published=true, recommend=true, createTime=null, updateTime=null, type=Type{id=1, name='null'}, tags=[], user=null, comments=[], tagIds='1,8', description='维护', typeId=1, userId=null}
        blog.setUser((User) session.getAttribute("user"));
        //将数据存入时，前端页面填数据时，由于是新增，type是在自己的表中进行查询（添加时已经查询过），
        // 因此设置时要从typeService中根据id拿到对应的type，而这个id应该是blog中进行封装后的type的id
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds())); //这里涉及多个标签
        //对外键进行设置，第二行中type已经有值了，因此直接可以查到对于的id
        blog.setTypeId(blog.getType().getId());
        //以下步骤多此一举，传入时tagId有值，且是“1,2,3”形式(理解错误所致,o(╥﹏╥)o)
        //将多个tag标签设置进blog中
//        StringBuilder builder=new StringBuilder();
//        for (Tag tag : blog.getTags()) {
//            builder.append(tag.getId());   //这里不能直接拼接，不然不知道id为多少，例如拼接1,2,3为"123",如果tagId=12那完蛋了
//        }                                  //可以每拼接一个加一个空格，取出时split分割
//        blog.setTagIds(builder.toString());
        blog.setUserId(blog.getUser().getId());
        int blog1 = blogService.saveBlog(blog);
        if (blog1 == 0) {
            attributes.addFlashAttribute("message","操作失败");
        } else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }
}

