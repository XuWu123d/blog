package com.example.blogofmybatis.service;

import com.example.blogofmybatis.dao.BlogDao;
import com.example.blogofmybatis.dao.TagDao;
import com.example.blogofmybatis.handler.NotFoundException;
import com.example.blogofmybatis.pojo.Blog;
import com.example.blogofmybatis.util.MackdownUtils;
import com.example.blogofmybatis.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogDao blogDao;
//    @Autowired
//    private TagDao tagDao;
    @Autowired
    private TagServiceImpl tagService;

    @Override
    public List<Blog> findAll(Page page) {
        return blogDao.findAll(page);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public Long countBlog() {
        return blogDao.count();
    }

    public Page page(Page page) {
        Long end = page.getEnd();  //获取一页的博客数量
        Long countBlog =blogDao.count(); //博客总数
        Long totalPage;     //博客总页
        if (countBlog % end == 0) {
            totalPage = countBlog / end;
        } else {
            totalPage = countBlog / end + 1;
        }
        Long pageNum = page.getPageNum();
        Long start = (pageNum - 1) * end;  //当前页码的开始索引
        page.setStart(start);
        page.setTotalPage(totalPage);
        page.setCount(countBlog);
        return page;
    }

    //保存博客
    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        int result = blogDao.saveBlog(blog);
        //对博客和标签两张表之间的关系进行维护
        //踩了个大坑，String转为Integer与Long转换出现问题，
        // 我之前将String用charAt以为取出的是字面对于的数字，但它转的是ASCII码，要用Integer
        Long blogId = blog.getId();//这里能取出id的神奇之处在mybatis框架对sql语句的处理（并没设置id，数据库自动增长，框架为我们封装并返回）
        String tagIds = blog.getTagIds();
        List<Long> tagIds1 = tagService.convertToList(tagIds);
        for (Long tagId : tagIds1) {
            //博客表与标签表的中间表的对应关系
            blogDao.blogToTags(blogId,tagId);
        }
        //用以下方法在id为1~9时没有问题，但是当我测试id大于等于10时，因为我是直接将数进行拼接，并没有将它们分隔，1,2拼接后为12(不知道是1,2还是12)
//        long longTagId;
//        for (int i=0;i<tagIds.length();i++) {
//            longTagId=Integer.parseInt(tagIds.charAt(i)+"");
//            Long tagId=new Long(longTagId);
//            System.out.println(tagId);
//            //博客表与标签表的中间表的对应关系
//            blogDao.blogToTags(blogId,tagId);
//        }
        return result;
    }

    //修改博客
    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blog.setTypeId(blog.getType().getId());
        //修改中间表
        Long blogId = blog.getId();
        //在数据库中将原来的数据查出并将其删除
        List<Long> tagIds = blogDao.findBlogToTagsByBlogId(blogId);
        for (Long tagId : tagIds) {
            blogDao.deleteBlogToTags(blogId,tagId);
        }
        //修改后的数据进行保存
        int result = blogDao.updateBlog(blog);
        //对修改后的标签与博客进行中间表维护
        String tagIds1 = blog.getTagIds();
        List<Long> list = tagService.convertToList(tagIds1);//对字符串进行转换
        for (Long id : list) {
            blogDao.blogToTags(blogId,id);
        }
        return result;
    }

    //删除博客
    @Transactional
    @Override
    public void deleteBlog(Long id) {    //踩坑，要先删除中间表，再删除blog
        //删除中间表的数据
        List<Long> tagIds = blogDao.findBlogToTagsByBlogId(id);
        for (Long tagId : tagIds) {
            blogDao.deleteBlogToTags(id,tagId);
        }
        blogDao.deleteBlog(id);
    }

    @Override
    public List<BlogIndex> findBlogIndex(Page page) {
        return blogDao.findBlogIndex(page);
    }

    @Override
    public List<BlogIndex> getBlogByTypeId(Long id) {
        return blogDao.getBlogByTypeId(id);
    }

    @Override
    public List<BlogIndex> getBlogByTagId(Long id) {
        return blogDao.getBlogByTagId(id);
    }

    //博客归档查询
    @Override
    public Map<String, List<Blog>> getArchiveBlog() {
        List<String> blogYear = blogDao.getBlogYear();
        Map<String, List<Blog>> map=new HashMap<>();
        for (String year : blogYear) {
            map.put(year,blogDao.getBlogByYear(year));
        }
        return map;
    }

    //获取博客详情
    @Override
    public BlogDetail getBlogConvert(Long id) {
        BlogDetail blogDetail = blogDao.getBlogDetailById(id);
        if (blogDetail==null) {
            throw new NotFoundException("该博客不存在");
        }
        //对博客详情进行转换
        String content = blogDetail.getContent();
        blogDetail.setContent(MackdownUtils.markdownToHtmlExtensions(content));
        blogDao.updateViews(id);
        return blogDetail;
    }


    @Override
    public List<BlogIndex> searchBlog(String query) {
        return blogDao.searchBlog(query);
    }

    @Override
    public List<Blog> listBlog(SearchBlog blog) {
        return blogDao.listBlog(blog);
    }

    @Override
    public List<BlogTypeWithCount> getBlogCountByTypeId() {
        return blogDao.getBlogCountByTypeId();
    }

    @Override
    public List<BlogTagWithCount> getBlogCountByTagId() {
        return blogDao.getBlogCountByTagId();
    }
}
