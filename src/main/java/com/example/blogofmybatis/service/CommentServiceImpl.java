package com.example.blogofmybatis.service;

import com.example.blogofmybatis.dao.CommentDao;
import com.example.blogofmybatis.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentDao commentDao;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys=new ArrayList<>();

    //根据创建时间先后给评论排序并展示
//    @Override
//    public List<Comment> ListCommentByBlogId(Long id) {
////        Sort sort=Sort.by(Sort.Direction.ASC,"createTime");
//        //拿到顶级父评论
////        List<Comment> comments = commentDao.findByBlogIdAndCommentNull(id, sort);
//        List<Comment> comments = commentDao.ListCommentByBlogId(id);
//
//        return eachComment(comments);
//    }


    @Override
    public List<Comment> ListCommentByBlogId(Long blogId) {
        //获取所有顶级评论
        List<Comment> parentComments = commentDao.ListCommentByBlogParentId(blogId);
        for (Comment comment : parentComments) {
            Long id = comment.getId();
            String parentNickname = comment.getNickname();
            //查询一级子评论
            List<Comment> childComments = commentDao.ListCommentByBlogParentNotNullId(blogId,id);
            eachComments(blogId,childComments,parentNickname);
            comment.setComments(tempReplys);
            tempReplys=new ArrayList<>();
        }
        return parentComments;
    }

    //对一级评论进行处理
    public void eachComments(Long blogId,List<Comment> childComments,String parentNickname) {
        if (childComments.size()>0) {
            for (Comment child : childComments) {
                String childNickname = child.getNickname();
                child.setCommentNickname(parentNickname);
                tempReplys.add(child);
                res(blogId,child,childNickname);
            }
        }
    }

    //对二级评论级更多子评论进行处理
    public void res(Long blogId,Comment comment,String parentNickname) {
        Long id = comment.getId();
        //获取二级子评论
        List<Comment> comments = commentDao.ListCommentByBlogParentNotNullId(blogId, id);
        if (comments.size()>0) {
            for (Comment comment1 : comments) {
                String nickname = comment1.getNickname();
                comment1.setCommentNickname(parentNickname);
                tempReplys.add(comment1);
                res(blogId,comment1,nickname);
            }
        }
    }

    //回复记录保存
    @Transactional
    @Override
    public int saveComment(Comment comment) {
        //获取被回复者id(获取父级id)
        Long id = comment.getComment().getId();
        //-1代表没有值（前端中设置过）
        if (id!=-1) {
            //设置评论对应关系
            comment.setCommentId(id);
        }
        //前端中并没有设置创建时间，因此要添加创建时间
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }

//    //删除评论
//    @Override
//    public void deleteComment(Long id) {
//        commentDao.deleteComment(id);
//    }
}
