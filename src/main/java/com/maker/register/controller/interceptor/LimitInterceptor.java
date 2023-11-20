package com.maker.register.controller.interceptor;

import com.maker.register.annotation.AccessLimit;
import com.maker.register.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 该过滤器通过注解以及redis对访问进行限制
 * */
public class LimitInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER= LoggerFactory.getLogger(LimitInterceptor.class);
    @Autowired
    private RedisScript<Boolean> limitScript;
    private static final String PREFIX="jayjxia";
    private static final String SPLIT=":";
    @Autowired
    @Qualifier("stringRedisTemplate")
    private RedisTemplate<String,String> stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod h=(HandlerMethod) handler;
           AccessLimit accessLimit= h.getMethodAnnotation(AccessLimit.class);
            if(ObjectUtils.isEmpty(accessLimit)){
               //未加上该注解，即不需要限制访问，直接放行
                return true;
            }
        String ip= IpUtil.getIp(request);
            String key=PREFIX+SPLIT+accessLimit.module()+SPLIT+ip;
            int limit=accessLimit.limit();

            Boolean result=this.stringRedisTemplate.execute(this.limitScript, List.of(key),String.valueOf(limit));
            LOGGER.info("【LimitInterceptor】{}",result);
            if(!result){
                //达到限制
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("请不要频繁访问该页面");
                return false;
            }

        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
