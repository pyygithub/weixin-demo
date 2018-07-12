package com.pyy.weixin.service.impl;

import com.pyy.weixin.config.WeChatAccountConfig;
import com.pyy.weixin.dto.OrderDTO;
import com.pyy.weixin.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/7/12 0012.
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService{

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeChatAccountConfig accountConfig;
    /**
     * 订单状态消息
     * @param orderDTO
     */
    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());

        // 这里需要和服务号中的模板定义结合使用
        List<WxMpTemplateData> wxMpTemplateDatas = Arrays.asList(
                new WxMpTemplateData("first","亲，记得收货。"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("keyword2","15888888888"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5", "￥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临")

        );
        templateMessage.setData(wxMpTemplateDatas);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败");
        }
    }
}
