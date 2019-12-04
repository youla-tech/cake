package org.jeecg.common.util;

import org.jeecg.common.util.encryption.AesEncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Kezai_Su on 2019/11/8.
 */
@Component
public class KaptchUtils {
  private static Logger logger = LoggerFactory.getLogger(IPUtils.class);
  private static final int EXPIRE_TIME = 2 * 60 * 60 * 1000;

  @Autowired
  private RedisUtil redisUtil;

  public void set(String code) {
    try{
      String ip = IPUtils.getIpAddr(SpringContextUtils.getHttpServletRequest());
      String key = AesEncryptUtil.encrypt(ip);
      redisUtil.set(key, code);
      redisUtil.expire(key, EXPIRE_TIME / 1000);
      logger.info("kaptcha key", key);
    }catch(Exception e){
      logger.info("kaptcha err", e);
    }
  }
  public String get(){
    String code = "";
    try {
      String ip = IPUtils.getIpAddr(SpringContextUtils.getHttpServletRequest());
      code = (String)redisUtil.get(AesEncryptUtil.encrypt(ip));
    }catch (Exception e) {
    }
    return code;
  }
  public void remove() {
    try {
      String ip = IPUtils.getIpAddr(SpringContextUtils.getHttpServletRequest());
      redisUtil.del(AesEncryptUtil.encrypt(ip));
    }catch (Exception e) {
    }
  }
}
