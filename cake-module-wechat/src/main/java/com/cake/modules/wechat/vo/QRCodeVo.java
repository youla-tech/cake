package com.cake.modules.wechat.vo;

import lombok.Data;

/**
 * Created by Kezai_Su on 2019/11/29.
 */
@Data
public class QRCodeVo {
  /**
   * 授权临时票据，用户确认授权不为空
   */
  private String code;
  /**
   * 防止csrf攻击（跨站请求伪造攻击），授权时带过去，授权成功后，回调返回
   */
  private String state;
}
