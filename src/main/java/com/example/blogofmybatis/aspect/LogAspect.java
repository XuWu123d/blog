package com.example.blogofmybatis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

//使用面向切面编程对日志信息进行管理
@Aspect
@Component
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.example.blogofmybatis.controller.*.*(..))")
    public void log() { }
    @Before("log()")  //前置通知
    public void before(JoinPoint joinPoint) {  //执行方法前在控制台输出执行方法的URL，IP，方法名，参数的日志信息
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
        logger.info("Result:{}",requestLog);
        System.out.println("=========before==========");
    }
    @After("log()")
    public void after() {
        System.out.println("==========after=========");
    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void afterReturn(Object result) {
        logger.info("result:{}",result);
        System.out.println("=========afterReturn=========");
    }

}
