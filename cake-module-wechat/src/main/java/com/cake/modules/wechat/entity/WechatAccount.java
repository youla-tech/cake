package com.cake.modules.wechat.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: wechat account
 * @Author: eamiear
 * @Date:   2019-12-03
 * @Version: V1.0
 */
@Data
@TableName("wx_account")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_account对象", description="wechat account")
public class WechatAccount {

	/**公众号账户*/
	@Excel(name = "公众号账户", width = 15)
  @ApiModelProperty(value = "公众号账户")
	private java.lang.String account;
	/**加密密钥*/
	@Excel(name = "加密密钥", width = 15)
  @ApiModelProperty(value = "加密密钥")
	private java.lang.String aeskey;
	/**公众号appid*/
	@Excel(name = "公众号appid", width = 15)
  @ApiModelProperty(value = "公众号appid")
	private java.lang.String appid;
	/**公众号密钥*/
	@Excel(name = "公众号密钥", width = 15)
  @ApiModelProperty(value = "公众号密钥")
	private java.lang.String appsecret;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
  @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**主键*/
	@TableId(type = IdType.UUID)
  @ApiModelProperty(value = "主键")
	private java.lang.Integer id;
	/**公众号名称*/
	@Excel(name = "公众号名称", width = 15)
  @ApiModelProperty(value = "公众号名称")
	private java.lang.String name;
	/**二维码图片URL*/
	@Excel(name = "二维码图片URL", width = 15)
  @ApiModelProperty(value = "二维码图片URL")
	private java.lang.String qrUrl;
	/**公众号token*/
	@Excel(name = "公众号token", width = 15)
  @ApiModelProperty(value = "公众号token")
	private java.lang.String token;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
  @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;
	/**公众号url*/
	@Excel(name = "公众号url", width = 15)
  @ApiModelProperty(value = "公众号url")
	private java.lang.String url;
}
