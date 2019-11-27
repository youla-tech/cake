package com.cake.modules.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
@Api(tags = "微信")
@Slf4j
public class WechatController {

  @GetMapping("/test")
  @ApiOperation("微信测试接口")
  public Result<Object> test() {
    return new Result<>().success("测试成功");
  }
}
