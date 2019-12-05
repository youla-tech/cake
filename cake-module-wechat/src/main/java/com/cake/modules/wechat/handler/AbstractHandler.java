package com.cake.modules.wechat.handler;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kezai_Su on 2019/12/5.
 */
public abstract class AbstractHandler implements WxMpMessageHandler{
  protected final Logger logger = LoggerFactory.getLogger(getClass());
}
