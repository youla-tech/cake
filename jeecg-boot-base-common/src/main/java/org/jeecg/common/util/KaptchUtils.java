package org.jeecg.common.util;

import org.jeecg.common.util.encryption.AesEncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kezai_Su on 2019/11/8.
 */
public class KaptchUtils {
  private static Logger logger = LoggerFactory.getLogger(IPUtils.class);
  private static final int EXPIRE_TIME = 60 * 1000;

  public static void set(String code) {
    try{
      String ip = IPUtils.getIpAddr(SpringContextUtils.getHttpServletRequest());
      RedisUtil redisUtil = new RedisUtil();
      String key = AesEncryptUtil.encrypt(ip);
      redisUtil.set(key, code);
      redisUtil.expire(key, EXPIRE_TIME / 1000);
      logger.info("kaptch key", key);
    }catch(Exception e){
      logger.info("kaptch err", e);
    }
  }
  public static String get(){
    String code = "";
    try {
      String ip = IPUtils.getIpAddr(SpringContextUtils.getHttpServletRequest());
      RedisUtil redisUtil = new RedisUtil();
      code = (String)redisUtil.get(AesEncryptUtil.encrypt(ip));
    }catch (Exception e) {
    }
    return code;
  }
}
