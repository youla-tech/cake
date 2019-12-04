package com.cake.modules.wechat.service.impl;

import com.cake.modules.wechat.entity.WechatAccount;
import com.cake.modules.wechat.mapper.WechatAccountMapper;
import com.cake.modules.wechat.service.IWechatAccountService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: wechat account
 * @Author: eamiear
 * @Date:   2019-12-03
 * @Version: V1.0
 */
@Service
public class WechatAccountServiceImpl extends ServiceImpl<WechatAccountMapper, WechatAccount> implements IWechatAccountService {

}
