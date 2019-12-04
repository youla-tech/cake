package com.cake.modules.wechat.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import com.cake.modules.wechat.entity.WechatAccountFansTag;
import com.cake.modules.wechat.service.IWechatAccountFansTagService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 账户粉丝标签
 * @Author: eamiear
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="账户粉丝标签")
@RestController
@RequestMapping("/wechat/wechatAccountFansTag")
public class WechatAccountFansTagController {
	@Autowired
	private IWechatAccountFansTagService wechatAccountFansTagService;

	/**
	 * 分页列表查询
	 * @param wechatAccountFansTag wechatAccountFansTag实体类
	 * @param pageNo    页码
	 * @param pageSize  每页条数
	 * @param req       request
	 * @return
	 */
	@AutoLog(value = "账户粉丝标签-分页列表查询")
	@ApiOperation(value="账户粉丝标签-分页列表查询", notes="账户粉丝标签-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WechatAccountFansTag>> queryPageList(WechatAccountFansTag wechatAccountFansTag,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WechatAccountFansTag>> result = new Result<IPage<WechatAccountFansTag>>();
		QueryWrapper<WechatAccountFansTag> queryWrapper = QueryGenerator.initQueryWrapper(wechatAccountFansTag, req.getParameterMap());
		Page<WechatAccountFansTag> page = new Page<WechatAccountFansTag>(pageNo, pageSize);
		IPage<WechatAccountFansTag> pageList = wechatAccountFansTagService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * @param wechatAccountFansTag wechatAccountFansTag实体类
	 * @return
	 */
	@AutoLog(value = "账户粉丝标签-添加")
	@ApiOperation(value="账户粉丝标签-添加", notes="账户粉丝标签-添加")
	@PostMapping(value = "/add")
	public Result<WechatAccountFansTag> add(@RequestBody WechatAccountFansTag wechatAccountFansTag) {
		Result<WechatAccountFansTag> result = new Result<WechatAccountFansTag>();
		try {
			wechatAccountFansTagService.save(wechatAccountFansTag);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	 * 编辑
	 * @param wechatAccountFansTag wechatAccountFansTag实体类
	 * @return
	 */
	@AutoLog(value = "账户粉丝标签-编辑")
	@ApiOperation(value="账户粉丝标签-编辑", notes="账户粉丝标签-编辑")
	@PutMapping(value = "/edit")
	public Result<WechatAccountFansTag> edit(@RequestBody WechatAccountFansTag wechatAccountFansTag) {
		Result<WechatAccountFansTag> result = new Result<WechatAccountFansTag>();
		WechatAccountFansTag wechatAccountFansTagEntity = wechatAccountFansTagService.getById(wechatAccountFansTag.getId());
		if(wechatAccountFansTagEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wechatAccountFansTagService.updateById(wechatAccountFansTag);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}

	/**
	 * 通过id删除
	 * @param id wechatAccountFansTag id
	 * @return
	 */
	@AutoLog(value = "账户粉丝标签-通过id删除")
	@ApiOperation(value="账户粉丝标签-通过id删除", notes="账户粉丝标签-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wechatAccountFansTagService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids id列表
	 * @return
	 */
	@AutoLog(value = "账户粉丝标签-批量删除")
	@ApiOperation(value="账户粉丝标签-批量删除", notes="账户粉丝标签-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WechatAccountFansTag> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WechatAccountFansTag> result = new Result<WechatAccountFansTag>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wechatAccountFansTagService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	 * 通过id查询
	 * @param id wechatAccountFansTag id
	 * @return
	 */
	@AutoLog(value = "账户粉丝标签-通过id查询")
	@ApiOperation(value="账户粉丝标签-通过id查询", notes="账户粉丝标签-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WechatAccountFansTag> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WechatAccountFansTag> result = new Result<WechatAccountFansTag>();
		WechatAccountFansTag wechatAccountFansTag = wechatAccountFansTagService.getById(id);
		if(wechatAccountFansTag==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wechatAccountFansTag);
			result.setSuccess(true);
		}
		return result;
	}

  /**
   * 导出excel
   *
   * @param request  HttpServletRequest
   * @param response HttpServletResponse
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<WechatAccountFansTag> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              WechatAccountFansTag wechatAccountFansTag = JSON.parseObject(deString, WechatAccountFansTag.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wechatAccountFansTag, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<WechatAccountFansTag> pageList = wechatAccountFansTagService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "账户粉丝标签列表");
      mv.addObject(NormalExcelConstants.CLASS, WechatAccountFansTag.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("账户粉丝标签列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
   * 通过excel导入数据
   *
   * @param request  HttpServletRequest
   * @param response HttpServletResponse
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<WechatAccountFansTag> listWechatAccountFansTags = ExcelImportUtil.importExcel(file.getInputStream(), WechatAccountFansTag.class, params);
              wechatAccountFansTagService.saveBatch(listWechatAccountFansTags);
              return Result.ok("文件导入成功！数据行数:" + listWechatAccountFansTags.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}
