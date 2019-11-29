package com.cake.modules.wechat.service.impl;

import com.cake.modules.wechat.constants.WechatConstant;
import com.cake.modules.wechat.service.ICommonService;
import com.cake.modules.wechat.vo.QRCodeVo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Kezai_Su on 2019/11/29.
 */
@Slf4j
@Service
public class CommonServiceImpl implements ICommonService {

  @Autowired
  private WxMpService wxMpService;

  @Value("${wx.mp.redirectUrl}")
  private String wxRedirectUrl;

  @Value(value = "${wx.mp.csrfKey}")
  private String CSRF_KEY;

  @Override
  public String getQRCodeUrl() {
    String date = DateUtils.formatDate(new Date(), "yyyyMMdd");
    String state = MD5Util.MD5Encode(CSRF_KEY + date, "");
    return wxMpService.buildQrConnectUrl(wxRedirectUrl, WechatConstant.SCOPE, state);
  }

  @Override
  public Boolean wxAuthCallBack(QRCodeVo qrCodeVo) {
    String code = qrCodeVo.getCode();
    String state = qrCodeVo.getState();
    String openid = null;
    String token = null;
    if (code == null) return false;
    if (code != null && state != null) {
      String date = DateUtils.formatDate(new Date(), "yyyyMMdd");
      Boolean isNotCsrf = MD5Util.verify(CSRF_KEY + date, state, "");
      if (!isNotCsrf) return false;

      try {
        WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(code);
        openid = accessToken.getOpenId();
        token = accessToken.getAccessToken();

        WxMpUser user = wxMpService.oauth2getUserInfo(accessToken, null);
        log.info("wechat user info: ", user);
      } catch (WxErrorException e) {
        log.error(e.getMessage(), e);
      }
      return true;
    }
    return false;
  }

  @Override
  public Boolean checkSignature(String timestamp, String nonce, String signature) {
    return wxMpService.checkSignature(timestamp, nonce, signature);
  }
}
