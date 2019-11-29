package com.cake.modules.wechat.controller;

import com.cake.modules.wechat.service.ICommonService;
import com.cake.modules.wechat.vo.QRCodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/wx")
@Api(tags = "微信")
@Slf4j
public class WechatController {

  @Autowired
  private ICommonService commonService;

  @Autowired
  private WxMpService wxMpService;

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

  @GetMapping("/test")
  @ApiOperation("微信测试接口")
  public Result<Object> test() {
    Result result = new Result();
    result.success("测试成功");
    return result;
  }

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
}
