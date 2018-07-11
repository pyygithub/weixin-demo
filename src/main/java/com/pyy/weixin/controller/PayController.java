package com.pyy.weixin.controller;

import com.pyy.weixin.dto.OrderDTO;
import com.pyy.weixin.enums.ResultEnum;
import com.pyy.weixin.exception.SellException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2018/7/11 0011.
 */
@Controller
@RequestMapping("/pay")
public class PayController {


    @GetMapping("/create")
    public void createOrder(@RequestParam("orderId") String orderId,
                            @RequestParam("returnUrl") String returnUrl) {
        // 1. 查询订单信息：orderId
        OrderDTO orderDTO = new OrderDTO();
        if(orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2. 支付


    }
}
