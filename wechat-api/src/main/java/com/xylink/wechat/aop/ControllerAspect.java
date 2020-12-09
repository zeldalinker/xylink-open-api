package com.xylink.wechat.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-09
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(public * com.xylink.wechat.controller.*.*(..))")
    public void controller() {
        // Pointcut
    }

    @Around("controller()")
    public Object access(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        // 打印请求内容
        log.info("===============请求内容===============");
        log.info("请求地址:" + request.getRequestURL().toString());
        log.info("请求方式:" + request.getMethod());
        log.info("请求类方法:" + point.getSignature());
        log.info("请求类方法参数:" + Arrays.toString(point.getArgs()));
        log.info("===============请求内容===============");

        return point.proceed();
    }
}
