package com.pyy.weixin.aspect;

import com.pyy.weixin.constant.CookieConstant;
import com.pyy.weixin.constant.RedisConstant;
import com.pyy.weixin.exception.SellerAuthorizeException;
import com.pyy.weixin.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * AOP身份验证
 * Created by Administrator on 2018/7/12 0012.
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.pyy.weixin.controller.Seller*.*(..)) " +
            "&& !execution(public * com.pyy.weixin.controller.SellerUserController.*(..))")
    public void verify(){};

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null) {
            log.warn("【登录校验】Cookie中查询不到token");
            throw new SellerAuthorizeException();
        }

        // 去redis查询token对应的openId
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查询不到token");
            throw new SellerAuthorizeException();
        }
    }
}
