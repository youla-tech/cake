package com.cake.modules.cms.vo;

import lombok.Data;

/**
 * Created by Kezai_Su on 2020/3/27.
 */

@Data
public class ColumnInfoVO {

  // 是否根据根节点模糊查询
  private Boolean isRootColumnLike;

  // 父级栏目ID
  private String rootColumnId;

  // 所属层级
  private Integer level;

  // 栏目编码
  private String code;

  // code 是否模糊搜索
  private String isCodeLike;

  // 栏目名称
  private String name;
}
