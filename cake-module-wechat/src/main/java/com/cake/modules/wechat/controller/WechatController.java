package com.cake.modules.wechat.controller;

import com.cake.modules.wechat.service.ICommonService;
import com.cake.modules.wechat.vo.QRCodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/wx/portal")
@Api(tags = "微信授权")
@Slf4j
public class WechatController {

  @Autowired
  private ICommonService commonService;

  @Autowired
  private WxMpService wxMpService;

  @Autowired
  private WxMpMessageRouter messageRouter;


  @GetMapping("/test")
  @ApiOperation("微信测试接口")
  public Result<Object> test() {
    Result result = new Result();
    result.success("测试成功");
    return result;
  }

  @GetMapping("/check")
  @ApiOperation("验证消息的确来自微信服务器")
  public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    String signature = request.getParameter("signature");
    String nonce = request.getParameter("nonce");
    String timestamp = request.getParameter("timestamp");
    String echostr = request.getParameter("echostr");

    if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
      response.getWriter().println("非法请求");
      return;
    }
    if (StringUtils.isNotBlank(echostr)) {
      String echoStrOut = String.copyValueOf(echostr.toCharArray());
      response.getWriter().println(echoStrOut);
      return;
    }
  }

  /**
   * 验证某个公众号（多个公众号服务器确认接口）
   * @param appid       公众号appid
   * @param signature
   * @param timestamp
   * @param nonce
   * @param echostr
   * @return
   */
  @GetMapping(value = "/{appid}", produces = "text/plain;charset=utf-8")
  @ApiOperation("验证消息是否来自微信服务器")
  public String authGet(@PathVariable String appid,
                        @RequestParam(name = "signature", required = false) String signature,
                        @RequestParam(name = "timestamp", required = false) String timestamp,
                        @RequestParam(name = "nonce", required = false) String nonce,
                        @RequestParam(name = "echostr", required = false) String echostr) {
    log.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
    if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
      throw new IllegalArgumentException("请求参数非法，请核实");
    }
    if (!this.wxMpService.switchover(appid)) {
      throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实", appid));
    }
    if (wxMpService.checkSignature(timestamp, nonce, signature)) {
      return echostr;
    }
    return "非法请求";
  }

  /**
   * 开放平台，需要开发平台APPID和secret
   * @return
   */
  @GetMapping("/getQRCodeUrl")
  @ApiOperation(("获取微信登录二维码地址"))
  public Result<Object> getQRCodeUrl () {
    return new Result<>().success("get QRCode url success", commonService.getQRCodeUrl());
  }

  @GetMapping("/callback")
  @ApiOperation("微信授权回调")
  public Result<Object> callBack(@RequestBody QRCodeVo qrCodeVo) {
    if (commonService.wxAuthCallBack(qrCodeVo)) {
      return new Result<>().success("回调成功");
    } else {
      return new Result<>().error500("回调失败");
    }
  }

  public String post(@PathVariable String appid,
                     @RequestBody String requestBody,
                     @RequestParam("signature") String signature,
                     @RequestParam("timestamp") String timestamp,
                     @RequestParam("nonce") String nonce,
                     @RequestParam("openid") String openid,
                     @RequestParam(name = "encrypt_type", required = false) String encType,
                     @RequestParam(name = "msg_signature", required = false) String msgSignature) {
    log.info("\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
      openid, signature, encType, msgSignature, timestamp, nonce, requestBody);

    if (!this.wxMpService.switchover(appid)) {
      throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
    }

    if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
      throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
    }

    String out = null;
    if (encType == null) {
      // 明文传输的消息
      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
      WxMpXmlOutMessage outMessage = this.route(inMessage);
      if (outMessage == null) {
        return "";
      }

      out = outMessage.toXml();
    } else if ("aes".equalsIgnoreCase(encType)) {
      // aes加密的消息
      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxMpService.getWxMpConfigStorage(),
        timestamp, nonce, msgSignature);
      log.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
      WxMpXmlOutMessage outMessage = this.route(inMessage);
      if (outMessage == null) {
        return "";
      }

      out = outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
    }

    log.debug("\n组装回复信息：{}", out);
    return out;
  }
  private WxMpXmlOutMessage route(WxMpXmlMessage message) {
    try {
      return this.messageRouter.route(message);
    } catch (Exception e) {
      log.error("路由消息时出现异常！", e);
    }

    return null;
  }
}
