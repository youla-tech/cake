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
 * @Description: wechat  article
 * @Author: eamiear
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Data
@TableName("wx_news_article_item")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_news_article_item对象", description="wechat  article")
public class WechatArticle {

	/**作者*/
	@Excel(name = "作者", width = 15)
  @ApiModelProperty(value = "作者")
	private java.lang.String author;
	/**内容*/
	@Excel(name = "内容", width = 15)
  @ApiModelProperty(value = "内容")
	private java.lang.Object content;
	/**内容链接*/
	@Excel(name = "内容链接", width = 15)
  @ApiModelProperty(value = "内容链接")
	private java.lang.String contentSourceUrl;
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
	/**摘要*/
	@Excel(name = "摘要", width = 15)
  @ApiModelProperty(value = "摘要")
	private java.lang.String digest;
	/**主键*/
	@TableId(type = IdType.UUID)
  @ApiModelProperty(value = "主键")
	private java.lang.Integer id;
	/**是否可以留言*/
	@Excel(name = "是否可以留言", width = 15)
  @ApiModelProperty(value = "是否可以留言")
	private java.lang.String needOpenComment;
	/**图文ID*/
	@Excel(name = "图文ID", width = 15)
  @ApiModelProperty(value = "图文ID")
	private java.lang.String newsId;
	/**是否仅粉丝可以留言*/
	@Excel(name = "是否仅粉丝可以留言", width = 15)
  @ApiModelProperty(value = "是否仅粉丝可以留言")
	private java.lang.String onlyFansCanComment;
	/**文章排序*/
	@Excel(name = "文章排序", width = 15)
  @ApiModelProperty(value = "文章排序")
	private java.lang.Integer orderNo;
	/**图片路径*/
	@Excel(name = "图片路径", width = 15)
  @ApiModelProperty(value = "图片路径")
	private java.lang.String picPath;
	/**是否展示封面图片（0/1）*/
	@Excel(name = "是否展示封面图片（0/1）", width = 15)
  @ApiModelProperty(value = "是否展示封面图片（0/1）")
	private java.lang.String showCoverPic;
	/**上传微信，封面图片标识*/
	@Excel(name = "上传微信，封面图片标识", width = 15)
  @ApiModelProperty(value = "上传微信，封面图片标识")
	private java.lang.String thumbMediaId;
	/**标题*/
	@Excel(name = "标题", width = 15)
  @ApiModelProperty(value = "标题")
	private java.lang.String title;
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
