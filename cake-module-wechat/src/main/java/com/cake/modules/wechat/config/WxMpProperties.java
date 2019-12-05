package com.cake.modules.wechat.config;

import lombok.Data;
import org.jeecg.common.util.JsonUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * Created by Kezai_Su on 2019/12/5.
 */
@Data
@PropertySource(value = "classpath:wechat.properties", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "wx.mp")
public class WxMpProperties {
  private List<MpConfig> configs;

  @Data
  public static class MpConfig {
    /**
     * 公众号appid
     */
    private String appId;

    /**
     * 公众号app secret
     */
    private String secret;

    /**
     * 公众号token
     */
    private String token;

    /**
     * 公众号 EncodingAESKey
     */
    private String aesKey;
  }
  @Override
  public String toString() {
    return JsonUtils.toJson(this);
  }
}
