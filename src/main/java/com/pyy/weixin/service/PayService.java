package com.pyy.weixin.service;

import com.lly835.bestpay.model.PayResponse;
import com.pyy.weixin.dto.OrderDTO;

/**
 * Created by Administrator on 2018/7/11 0011.
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);
}
