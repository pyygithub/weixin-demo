package com.pyy.weixin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/7/11 0011.
 */
@RestController
public class UserController {

    @GetMapping("/list")
    public String list() {
        return "用户列表";
    }
}
