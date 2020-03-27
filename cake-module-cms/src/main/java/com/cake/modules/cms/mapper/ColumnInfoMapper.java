package com.cake.modules.cms.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cake.modules.cms.vo.ColumnInfoVO;
import com.cake.modules.cms.entity.ColumnInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: Column Info
 * @Author: eamiear
 * @Date:   2020-03-27
 * @Version: V1.0
 */
public interface ColumnInfoMapper extends BaseMapper<ColumnInfo> {

  IPage<ColumnInfo> queryColumnInfo(Page page, ColumnInfoVO columnInfoVO);

  List<ColumnInfo> queryColumnInfoList(ColumnInfoVO columnInfoVO);
}
