package com.cake.modules.cms.entity;

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
 * @Description: Column Info
 * @Author: eamiear
 * @Date:   2020-03-27
 * @Version: V1.0
 */
@Data
@TableName("cms_column_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cms_column_info对象", description="Column Info")
public class ColumnInfo {

	/**id*/
	@TableId(type = IdType.UUID)
  @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**auditFlag*/
	@Excel(name = "auditFlag", width = 15)
  @ApiModelProperty(value = "auditFlag")
	private java.lang.String auditFlag;
	/**createDate*/
	@Excel(name = "createDate", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "createDate")
	private java.util.Date createDate;
	/**deleteFlag*/
	@Excel(name = "deleteFlag", width = 15)
  @ApiModelProperty(value = "deleteFlag")
	private java.lang.String deleteFlag;
	/**updateDate*/
	@Excel(name = "updateDate", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "updateDate")
	private java.util.Date updateDate;
	/**编码*/
	@Excel(name = "编码", width = 15)
  @ApiModelProperty(value = "编码")
	private java.lang.String code;
	/**层级*/
	@Excel(name = "层级", width = 15)
  @ApiModelProperty(value = "层级")
	private java.lang.Integer level;
	/**名称*/
	@Excel(name = "名称", width = 15)
  @ApiModelProperty(value = "名称")
	private java.lang.String name;
	/**排序*/
	@Excel(name = "排序", width = 15)
  @ApiModelProperty(value = "排序")
	private java.lang.Integer orderNo;
	/**path*/
	@Excel(name = "path", width = 15)
  @ApiModelProperty(value = "path")
	private java.lang.String path;
	/**父级id*/
	@Excel(name = "父级id", width = 15)
  @ApiModelProperty(value = "父级id")
	private java.lang.String parentId;
	/**图标*/
	@Excel(name = "图标", width = 15)
  @ApiModelProperty(value = "图标")
	private java.lang.String icon;
	/**渠道*/
	@Excel(name = "渠道", width = 15)
  @ApiModelProperty(value = "渠道")
	private java.lang.Integer channel;
}
