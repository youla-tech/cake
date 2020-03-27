package com.cake.modules.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cake.modules.cms.entity.ColumnInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cake.modules.cms.vo.ColumnInfoVO;

import java.util.List;

/**
 * @Description: Column Info
 * @Author: eamiear
 * @Date:   2020-03-27
 * @Version: V1.0
 */
public interface IColumnInfoService extends IService<ColumnInfo> {

  public IPage<ColumnInfo> queryColumnInfo(Page<ColumnInfo> page, ColumnInfoVO columnInfoVO);

  public List<ColumnInfo> queryColumnInfoList(ColumnInfoVO columnInfoVO);
}
