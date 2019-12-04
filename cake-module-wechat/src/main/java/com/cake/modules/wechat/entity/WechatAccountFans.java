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
 * @Description: wechat account fans
 * @Author: eamiear
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Data
@TableName("wx_account_fans")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_account_fans对象", description="wechat account fans")
public class WechatAccountFans {

	/**城市*/
	@Excel(name = "城市", width = 15)
  @ApiModelProperty(value = "城市")
	private java.lang.String city;
	/**国家*/
	@Excel(name = "国家", width = 15)
  @ApiModelProperty(value = "国家")
	private java.lang.String country;
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
	/**性别，1男，2女，0未知*/
	@Excel(name = "性别，1男，2女，0未知", width = 15)
  @ApiModelProperty(value = "性别，1男，2女，0未知")
	private java.lang.String gender;
	/**头像地址*/
	@Excel(name = "头像地址", width = 15)
  @ApiModelProperty(value = "头像地址")
	private java.lang.String headimgUrl;
	/**主键*/
	@TableId(type = IdType.UUID)
  @ApiModelProperty(value = "主键")
	private java.lang.Integer id;
	/**语言*/
	@Excel(name = "语言", width = 15)
  @ApiModelProperty(value = "语言")
	private java.lang.String language;
	/**昵称*/
	@Excel(name = "昵称", width = 15)
  @ApiModelProperty(value = "昵称")
	private java.lang.Object nickname;
	/**用户标识*/
	@Excel(name = "用户标识", width = 15)
  @ApiModelProperty(value = "用户标识")
	private java.lang.String openid;
	/**省份*/
	@Excel(name = "省份", width = 15)
  @ApiModelProperty(value = "省份")
	private java.lang.String province;
	/**备注*/
	@Excel(name = "备注", width = 15)
  @ApiModelProperty(value = "备注")
	private java.lang.String remark;
	/**订阅状态，0未关注，1已关注*/
	@Excel(name = "订阅状态，0未关注，1已关注", width = 15)
  @ApiModelProperty(value = "订阅状态，0未关注，1已关注")
	private java.lang.String subscribeStatus;
	/**订阅时间*/
	@Excel(name = "订阅时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "订阅时间")
	private java.util.Date subscribeTime;
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
	/**微信公众号appid*/
	@Excel(name = "微信公众号appid", width = 15)
  @ApiModelProperty(value = "微信公众号appid")
	private java.lang.String wxAccountAppid;
	/**微信公众号ID*/
	@Excel(name = "微信公众号ID", width = 15)
  @ApiModelProperty(value = "微信公众号ID")
	private java.lang.String wxAccountId;
}
