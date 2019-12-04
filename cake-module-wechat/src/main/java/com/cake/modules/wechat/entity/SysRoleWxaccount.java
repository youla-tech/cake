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
 * @Description: Role Wechat Account
 * @Author: eamiear
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Data
@TableName("sys_role_wxaccount")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_role_wxaccount对象", description="Role Wechat Account")
public class SysRoleWxaccount {

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
	/**角色ID*/
	@Excel(name = "角色ID", width = 15)
  @ApiModelProperty(value = "角色ID")
	private java.lang.String roleId;
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
