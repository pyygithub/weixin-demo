package com.pyy.weixin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信公众号信息配置
 * Created by Administrator on 2018/7/11 0011.
 */

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    /**
     * 公众平台appID
     */
    private String mpAppId;

    /**
     * 公众平台appsecret
     */
    private String mpAppSecret;

    /**
     * 开发平台appId
     */
    private String openAppId;

    /**
     * 开发平台secret
     */
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;
}
