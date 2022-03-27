package com.example.blogofmybatis.service;

import com.example.blogofmybatis.dao.TypeDao;
import com.example.blogofmybatis.handler.NotFoundException;
import com.example.blogofmybatis.pojo.Type;
import com.example.blogofmybatis.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    private TypeDao typeDao;
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Transactional //放在事务中
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }

    @Override
    public List<Type> getListType(Page page) {
        return typeDao.getListType(page);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        Type t=typeDao.getType(type.getId());
        if (t==null) {
            throw new NotFoundException("不存在该类型");
        }
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    @Override
    public Page page(Page page) {
        Long countType = typeDao.countType();
        Long pageNum = page.getPageNum();
        Long end = page.getEnd();
        Long start=(pageNum - 1) * end;
        Long totalPage;
        if (countType % end == 0) {
            totalPage = countType / end;
        } else {
            totalPage = countType / end + 1;
        }
        page.setTotalPage(totalPage);
        page.setStart(start);
        page.setCount(countType);
        return page;
    }

}
