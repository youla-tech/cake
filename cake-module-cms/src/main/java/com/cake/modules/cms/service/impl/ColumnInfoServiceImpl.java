package com.cake.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cake.modules.cms.entity.ColumnInfo;
import com.cake.modules.cms.mapper.ColumnInfoMapper;
import com.cake.modules.cms.service.IColumnInfoService;
import com.cake.modules.cms.vo.ColumnInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: Column Info
 * @Author: eamiear
 * @Date:   2020-03-27
 * @Version: V1.0
 */
@Service
public class ColumnInfoServiceImpl extends ServiceImpl<ColumnInfoMapper, ColumnInfo> implements IColumnInfoService {

  @Autowired
  private ColumnInfoMapper columnInfoMapper;

  @Override
  public IPage<ColumnInfo> queryColumnInfo(Page<ColumnInfo> page, ColumnInfoVO columnInfoVO) {
    return null;
  }

  @Override
  public List<ColumnInfo> queryColumnInfoList(ColumnInfoVO columnInfoVO) {
    return null;
  }
}
