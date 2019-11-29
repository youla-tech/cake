package com.cake.modules.wechat.service;

import com.cake.modules.wechat.vo.QRCodeVo;

/**
 * Created by Kezai_Su on 2019/11/29.
 */
public interface ICommonService {
  /**
   * 微信扫码登录请求地址
   * @return
   */
  String getQRCodeUrl ();

  /**
   * 微信扫码后回调
   * @param qrCodeVo
   * @return
   */
  Boolean wxAuthCallBack(QRCodeVo qrCodeVo);

  /**
   *
   * @param timestamp
   * @param nonce
   * @param signature
   * @return
   */
  Boolean checkSignature(String timestamp, String nonce, String signature);
}
