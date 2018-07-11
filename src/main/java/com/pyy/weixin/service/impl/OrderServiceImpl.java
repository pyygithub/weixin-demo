package com.pyy.weixin.service.impl;


import com.pyy.weixin.dto.OrderDTO;
import com.pyy.weixin.service.OrderService;
import com.pyy.weixin.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 廖师兄
 * 2017-06-11 18:43
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PayService payService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {

        // 如果已支付，退款
//        if(orderDTO.getPayStatus()) {
//            payService.refund(orderDTO);
//        }


        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
