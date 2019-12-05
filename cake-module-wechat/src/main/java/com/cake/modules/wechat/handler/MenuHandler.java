package com.cake.modules.wechat.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Kezai_Su on 2019/12/5.
 */

@Component
public class MenuHandler extends AbstractHandler{
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
    String msg = wxMpXmlMessage.getEventKey();
    if (WxConsts.MenuButtonType.VIEW.equals(wxMpXmlMessage.getEvent())) {
      return null;
    }
    return WxMpXmlOutMessage.TEXT().content(msg)
      .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
      .build();
  }
}
