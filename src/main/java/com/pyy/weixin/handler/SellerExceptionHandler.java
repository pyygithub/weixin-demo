package com.pyy.weixin.handler;

import com.pyy.weixin.enums.ResultEnum;
import com.pyy.weixin.exception.SellerAuthorizeException;
import com.pyy.weixin.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 * Created by Administrator on 2018/7/12 0012.
 */
@ControllerAdvice
@Slf4j
public class SellerExceptionHandler {

    /**
     * 拦截登录异常
     * @return
     */
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public Result handlerAuthorizeException() {
        log.info("【用户登录拦截】用户未登录，需要去登录页面登录");
        return new Result(ResultEnum.NEED_LOGIN);
    }
}
