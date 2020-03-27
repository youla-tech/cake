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
 * @Description: Article
 * @Author: eamiear
 * @Date:   2020-03-27
 * @Version: V1.0
 */
@Data
@TableName("cms_article")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cms_article对象", description="Article")
public class Article {

	/**id*/
	@TableId(type = IdType.UUID)
  @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**审核*/
	@Excel(name = "审核", width = 15)
  @ApiModelProperty(value = "审核")
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
	/**文章内容*/
	@Excel(name = "文章内容", width = 15)
  @ApiModelProperty(value = "文章内容")
	private java.lang.Object content;
	/**封面图片*/
	@Excel(name = "封面图片", width = 15)
  @ApiModelProperty(value = "封面图片")
	private java.lang.String coverImageUrl;
	/**跳转url*/
	@Excel(name = "跳转url", width = 15)
  @ApiModelProperty(value = "跳转url")
	private java.lang.String href;
	/**排序*/
	@Excel(name = "排序", width = 15)
  @ApiModelProperty(value = "排序")
	private java.lang.Integer orderNo;
	/**发布者*/
	@Excel(name = "发布者", width = 15)
  @ApiModelProperty(value = "发布者")
	private java.lang.String publisher;
	/**资源来源*/
	@Excel(name = "资源来源", width = 15)
  @ApiModelProperty(value = "资源来源")
	private java.lang.String sourceFrom;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
  @ApiModelProperty(value = "摘要")
	private java.lang.String summary;
	/**标题*/
	@Excel(name = "标题", width = 15)
  @ApiModelProperty(value = "标题")
	private java.lang.String title;
	/**columnInfoId*/
	@Excel(name = "columnInfoId", width = 15)
  @ApiModelProperty(value = "columnInfoId")
	private java.lang.String columnInfoId;
	/**是否审核*/
	@Excel(name = "是否审核", width = 15)
  @ApiModelProperty(value = "是否审核")
	private java.lang.Object isAudit;
	/**是否置顶*/
	@Excel(name = "是否置顶", width = 15)
  @ApiModelProperty(value = "是否置顶")
	private java.lang.Object isTop;
	/**访问数*/
	@Excel(name = "访问数", width = 15)
  @ApiModelProperty(value = "访问数")
	private java.lang.Integer viewCount;
	/**rootColumnInfoId*/
	@Excel(name = "rootColumnInfoId", width = 15)
  @ApiModelProperty(value = "rootColumnInfoId")
	private java.lang.String rootColumnInfoId;
	/**文章类型（内容文章、外链文章、广告位）*/
	@Excel(name = "文章类型（内容文章、外链文章、广告位）", width = 15)
  @ApiModelProperty(value = "文章类型（内容文章、外链文章、广告位）")
	private java.lang.Integer type;
}
