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
 * @Description: wechat  menu
 * @Author: eamiear
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Data
@TableName("wx_menu")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_menu对象", description="wechat  menu")
public class WechatMenu {

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
	/**菜单等级*/
	@Excel(name = "菜单等级", width = 15)
  @ApiModelProperty(value = "菜单等级")
	private java.lang.String menuLevel;
	/**菜单名称*/
	@Excel(name = "菜单名称", width = 15)
  @ApiModelProperty(value = "菜单名称")
	private java.lang.String menuName;
	/**排序*/
	@Excel(name = "排序", width = 15)
  @ApiModelProperty(value = "排序")
	private java.lang.String menuSort;
	/**菜单类型 1文本消息；2图文消息；3网址链接；4小程序*/
	@Excel(name = "菜单类型 1文本消息；2图文消息；3网址链接；4小程序", width = 15)
  @ApiModelProperty(value = "菜单类型 1文本消息；2图文消息；3网址链接；4小程序")
	private java.lang.String menuType;
	/**菜单URL*/
	@Excel(name = "菜单URL", width = 15)
  @ApiModelProperty(value = "菜单URL")
	private java.lang.String menuUrl;
	/**小程序appid*/
	@Excel(name = "小程序appid", width = 15)
  @ApiModelProperty(value = "小程序appid")
	private java.lang.String miniprogramAppid;
	/**小程序页面路径*/
	@Excel(name = "小程序页面路径", width = 15)
  @ApiModelProperty(value = "小程序页面路径")
	private java.lang.String miniprogramPagepath;
	/**父ID*/
	@Excel(name = "父ID", width = 15)
  @ApiModelProperty(value = "父ID")
	private java.lang.String parentId;
	/**模板ID*/
	@Excel(name = "模板ID", width = 15)
  @ApiModelProperty(value = "模板ID")
	private java.lang.String tplId;
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
