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
 * @Description: wechat  text template
 * @Author: eamiear
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Data
@TableName("wx_text_template")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_text_template对象", description="wechat  text template")
public class WechatTextTemplate {

	/**模板内容*/
	@Excel(name = "模板内容", width = 15)
  @ApiModelProperty(value = "模板内容")
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
	/**主键*/
	@TableId(type = IdType.UUID)
  @ApiModelProperty(value = "主键")
	private java.lang.Integer id;
	/**模板名字*/
	@Excel(name = "模板名字", width = 15)
  @ApiModelProperty(value = "模板名字")
	private java.lang.String tplName;
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
}
