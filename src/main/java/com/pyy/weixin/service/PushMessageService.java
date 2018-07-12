package com.pyy.weixin.service;

import com.pyy.weixin.dto.OrderDTO;

/**
 * 消息推送
 * Created by Administrator on 2018/7/12 0012.
 */
public interface PushMessageService {

    void orderStatus(OrderDTO orderDTO);
}
