package com.pyy.weixin.service;


import com.pyy.weixin.model.SellerInfo;

/**
 * 卖家端Service
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
