package com.cake.modules.wechat.config;

import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Kezai_Su on 2019/11/29.
 */

//@Configuration
//@PropertySource(value = "classpath:wechat.properties", ignoreResourceNotFound = true)
@Data
public class WxOpenConfig {
  @Value("${wx.mp.appid}")
  private String appid;

  @Value("${wx.mp.secret}")
  private String secret;

  @Value("${wx.mp.token}")
  private String token;

  @Bean
  public WxMpService wxMpService() {
    WxMpService service = new WxMpServiceImpl();
    WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
    configStorage.setAppId(appid);
    configStorage.setSecret(secret);
    configStorage.setToken(token);
    service.setWxMpConfigStorage(configStorage);
    return service;
  }
}
