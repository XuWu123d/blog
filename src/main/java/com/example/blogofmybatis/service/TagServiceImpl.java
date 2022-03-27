package com.example.blogofmybatis.service;

import com.example.blogofmybatis.dao.TagDao;
import com.example.blogofmybatis.handler.NotFoundException;
import com.example.blogofmybatis.pojo.Tag;
import com.example.blogofmybatis.vo.BlogIndex;
import com.example.blogofmybatis.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    //分页查询
    @Override
    public List<Tag> pageTag(Page page) {
        return tagDao.pageTag(page);
    }

    public Page page(Page page) {
        Long countTag = tagDao.count();
        Long end = page.getEnd();
        Long pageNum = page.getPageNum();
        Long start = (pageNum - 1) * end;
        Long totalPage;
        if (countTag % end == 0) {
            totalPage = countTag / end;
        } else {
            totalPage = countTag / end + 1;
        }
        page.setTotalPage(totalPage);
        page.setCount(countTag);
        page.setStart(start);
        return page;
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.listTag();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagDao.findAllById(convertToList(ids));
    }

    public List<Long> convertToList(String ids) {
        List<Long> list=new ArrayList<>();
        if (!"".equals(ids) && ids!=null) {
            String[] idarray=ids.split(",");
            for (int i=0;i<idarray.length;i++) {
                 list.add(Long.valueOf(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        Tag t = tagDao.getTag(tag.getId());
        if (t==null) {
            throw new NotFoundException("该标签不存在");
        }
        return tagDao.updateTag(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.deleteTag(id);
    }

    @Override
    public List<BlogIndex> getBlogByTagId(Long id) {
        return tagDao.getBlogByTagId(id);
    }
}
