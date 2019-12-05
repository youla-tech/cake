package com.cake.modules.wechat.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.jeecg.common.util.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Kezai_Su on 2019/12/5.
 */

@Component
public class LogHandler extends AbstractHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
    this.logger.info("\n接收到请求消息，内容：{}", JsonUtils.toJson(wxMpXmlMessage));
    return null;
  }
}
