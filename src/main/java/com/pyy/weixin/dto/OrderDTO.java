package com.pyy.weixin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pyy.weixin.enums.OrderStatusEnum;
import com.pyy.weixin.enums.PayStatusEnum;
import com.pyy.weixin.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 廖师兄
 * 2017-06-11 18:30
 */
@Data
public class OrderDTO {
    /** 订单id. */
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
