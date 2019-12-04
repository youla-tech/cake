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
 * @Description: wechat  fans msg response
 * @Author: eamiear
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Data
@TableName("wx_fans_msg_res")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_fans_msg_res对象", description="wechat  fans msg response")
public class WechatFansMsgResponse {

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
	/**粉丝消息ID*/
	@Excel(name = "粉丝消息ID", width = 15)
  @ApiModelProperty(value = "粉丝消息ID")
	private java.lang.String fansMsgId;
	/**主键*/
	@TableId(type = IdType.UUID)
  @ApiModelProperty(value = "主键")
	private java.lang.Integer id;
	/**回复内容*/
	@Excel(name = "回复内容", width = 15)
  @ApiModelProperty(value = "回复内容")
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
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
  @ApiModelProperty(value = "用户ID")
	private java.lang.String userId;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
  @ApiModelProperty(value = "用户名")
	private java.lang.String userName;
}
