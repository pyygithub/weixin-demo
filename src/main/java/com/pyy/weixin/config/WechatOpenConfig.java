package com.pyy.weixin.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信开发平台配置类
 * Created by Administrator on 2018/7/12 0012.
 */
@Configuration
public class WechatOpenConfig {
    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Bean
    public WxMpService wxOpenService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(weChatAccountConfig.getOpenAppId());
        wxMpConfigStorage.setSecret(weChatAccountConfig.getOpenAppSecret());

        return wxMpConfigStorage;
    }
}
