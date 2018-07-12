package com.pyy.weixin.controller;

import com.pyy.weixin.constant.CookieConstant;
import com.pyy.weixin.constant.RedisConstant;
import com.pyy.weixin.enums.ResultEnum;
import com.pyy.weixin.model.SellerInfo;
import com.pyy.weixin.service.SellerService;
import com.pyy.weixin.utils.CookieUtil;
import com.pyy.weixin.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户Controller
 * Created by Administrator on 2018/7/12 0012.
 */

@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 用户登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/login")
    public Result login(@RequestParam("openid") String openid, HttpServletResponse response){
        // 1. openid去数据库匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null) {
            return new Result(ResultEnum.LOGIN_FAIL);
        }

        // 2. 设置token到redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);
        log.info("【用户登录】设置token到redis");

        // 3. 设置token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        log.info("【用户登录】设置token到cookie");

        return new Result(ResultEnum.LOGIN_SUCCESS);
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response){
        // 1. 从cookie查询token
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie != null) {
            // 2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            log.info("【用户退出】清除redis");
            // 3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
            log.info("【用户退出】清除cookie");
        }

        return new Result(ResultEnum.LOGOUT_SUCCESS);
    }
}
