package com.example.blogofmybatis.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截器
@ControllerAdvice //拦截所有@controller的控制器
public class MyHandler {
    //获取日志
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class) //标识该方法可以做异常处理
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //记录异常信息（请求路径，记录异常信息）
        logger.error("Request URL :{},Exception:{}",request.getRequestURL(),e);
        //当异常标识了状态码时，交给springboot自己去处理（ResponseStatus.class返回true或false，存在不为空,抛异常）
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) !=null) {
            throw e;
        }

        ModelAndView mad=new ModelAndView();
        //前端页面获取路径
        mad.addObject("url",request.getRequestURL());
        //存储异常信息
        mad.addObject("exception",e);
        //跳转页面
        mad.setViewName("error/error");
        return mad;
    }
}

