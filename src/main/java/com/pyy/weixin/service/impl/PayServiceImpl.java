package com.pyy.weixin.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.pyy.weixin.dto.OrderDTO;
import com.pyy.weixin.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/11 0011.
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private  BestPayServiceImpl bestPayService;

    @Override
    public void create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderId("123456");
        payRequest.setOrderName("微信公众账号支付订单");
        payRequest.setOrderAmount(0.01);
        payRequest.setOpenid("openid_xxxxxx");

        log.info("【微信支付】request={}", payRequest);

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】response={}", payResponse);

    }
}
