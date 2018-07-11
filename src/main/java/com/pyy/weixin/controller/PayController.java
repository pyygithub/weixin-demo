package com.pyy.weixin.controller;

import com.lly835.bestpay.model.PayResponse;
import com.pyy.weixin.dto.OrderDTO;
import com.pyy.weixin.enums.ResultEnum;
import com.pyy.weixin.exception.SellException;
import com.pyy.weixin.service.OrderService;
import com.pyy.weixin.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/11 0011.
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public ModelAndView createOrder(@RequestParam("orderId") String orderId,
                                    @RequestParam("returnUrl") String returnUrl,
                                    Map<String, Object> model) {
        // 1. 查询订单信息：orderId
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2. 发起支付
        PayResponse payResponse = payService.create(orderDTO);
        model.put("payResponse", payResponse);
        model.put("returnUrl", returnUrl);

        // 模板路径： templates/pay/create.ftl
        return new ModelAndView("pay/create", model);

    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        // 返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
