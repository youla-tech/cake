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
 * @Description: wechat  fans msg
 * @Author: eamiear
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Data
@TableName("wx_fans_msg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_fans_msg对象", description="wechat  fans msg")
public class WechatFansMsg {

	/**内容*/
	@Excel(name = "内容", width = 15)
  @ApiModelProperty(value = "内容")
	private java.lang.String content;
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
	/**头像地址*/
	@Excel(name = "头像地址", width = 15)
  @ApiModelProperty(value = "头像地址")
	private java.lang.String headimgUrl;
	/**主键*/
	@TableId(type = IdType.UUID)
  @ApiModelProperty(value = "主键")
	private java.lang.Integer id;
	/**是否已回复*/
	@Excel(name = "是否已回复", width = 15)
  @ApiModelProperty(value = "是否已回复")
	private java.lang.String isRes;
	/**微信素材ID*/
	@Excel(name = "微信素材ID", width = 15)
  @ApiModelProperty(value = "微信素材ID")
	private java.lang.String mediaId;
	/**消息类型*/
	@Excel(name = "消息类型", width = 15)
  @ApiModelProperty(value = "消息类型")
	private java.lang.String msgType;
	/**昵称*/
	@Excel(name = "昵称", width = 15)
  @ApiModelProperty(value = "昵称")
	private java.lang.Object nickname;
	/**用户标识*/
	@Excel(name = "用户标识", width = 15)
  @ApiModelProperty(value = "用户标识")
	private java.lang.String openid;
	/**本地图片路径*/
	@Excel(name = "本地图片路径", width = 15)
  @ApiModelProperty(value = "本地图片路径")
	private java.lang.String picPath;
	/**微信图片URL*/
	@Excel(name = "微信图片URL", width = 15)
  @ApiModelProperty(value = "微信图片URL")
	private java.lang.String picUrl;
	/**最近一条回复内容*/
	@Excel(name = "最近一条回复内容", width = 15)
  @ApiModelProperty(value = "最近一条回复内容")
	private java.lang.Object resContent;
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
	/**微信账号ID*/
	@Excel(name = "微信账号ID", width = 15)
  @ApiModelProperty(value = "微信账号ID")
	private java.lang.String wxAccountId;
}
