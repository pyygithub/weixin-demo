package com.pyy.weixin.service;

import com.pyy.weixin.dto.OrderDTO;

/**
 * Created by Administrator on 2018/7/11 0011.
 */
public interface PayService {

    void create(OrderDTO orderDTO);
}
