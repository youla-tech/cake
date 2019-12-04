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
import com.cake.modules.wechat.entity.WechatTemplate;
import com.cake.modules.wechat.service.IWechatTemplateService;
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
 * @Description: 模板
 * @Author: eamiear
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="模板")
@RestController
@RequestMapping("/wechat/wechatTemplate")
public class WechatTemplateController {
	@Autowired
	private IWechatTemplateService wechatTemplateService;

	/**
	 * 分页列表查询
	 * @param wechatTemplate wechatTemplate实体类
	 * @param pageNo    页码
	 * @param pageSize  每页条数
	 * @param req       request
	 * @return
	 */
	@AutoLog(value = "模板-分页列表查询")
	@ApiOperation(value="模板-分页列表查询", notes="模板-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WechatTemplate>> queryPageList(WechatTemplate wechatTemplate,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WechatTemplate>> result = new Result<IPage<WechatTemplate>>();
		QueryWrapper<WechatTemplate> queryWrapper = QueryGenerator.initQueryWrapper(wechatTemplate, req.getParameterMap());
		Page<WechatTemplate> page = new Page<WechatTemplate>(pageNo, pageSize);
		IPage<WechatTemplate> pageList = wechatTemplateService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * @param wechatTemplate wechatTemplate实体类
	 * @return
	 */
	@AutoLog(value = "模板-添加")
	@ApiOperation(value="模板-添加", notes="模板-添加")
	@PostMapping(value = "/add")
	public Result<WechatTemplate> add(@RequestBody WechatTemplate wechatTemplate) {
		Result<WechatTemplate> result = new Result<WechatTemplate>();
		try {
			wechatTemplateService.save(wechatTemplate);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	 * 编辑
	 * @param wechatTemplate wechatTemplate实体类
	 * @return
	 */
	@AutoLog(value = "模板-编辑")
	@ApiOperation(value="模板-编辑", notes="模板-编辑")
	@PutMapping(value = "/edit")
	public Result<WechatTemplate> edit(@RequestBody WechatTemplate wechatTemplate) {
		Result<WechatTemplate> result = new Result<WechatTemplate>();
		WechatTemplate wechatTemplateEntity = wechatTemplateService.getById(wechatTemplate.getId());
		if(wechatTemplateEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wechatTemplateService.updateById(wechatTemplate);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}

	/**
	 * 通过id删除
	 * @param id wechatTemplate id
	 * @return
	 */
	@AutoLog(value = "模板-通过id删除")
	@ApiOperation(value="模板-通过id删除", notes="模板-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wechatTemplateService.removeById(id);
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
	@AutoLog(value = "模板-批量删除")
	@ApiOperation(value="模板-批量删除", notes="模板-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WechatTemplate> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WechatTemplate> result = new Result<WechatTemplate>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wechatTemplateService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	 * 通过id查询
	 * @param id wechatTemplate id
	 * @return
	 */
	@AutoLog(value = "模板-通过id查询")
	@ApiOperation(value="模板-通过id查询", notes="模板-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WechatTemplate> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WechatTemplate> result = new Result<WechatTemplate>();
		WechatTemplate wechatTemplate = wechatTemplateService.getById(id);
		if(wechatTemplate==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wechatTemplate);
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
      QueryWrapper<WechatTemplate> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              WechatTemplate wechatTemplate = JSON.parseObject(deString, WechatTemplate.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wechatTemplate, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<WechatTemplate> pageList = wechatTemplateService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "模板列表");
      mv.addObject(NormalExcelConstants.CLASS, WechatTemplate.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("模板列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WechatTemplate> listWechatTemplates = ExcelImportUtil.importExcel(file.getInputStream(), WechatTemplate.class, params);
              wechatTemplateService.saveBatch(listWechatTemplates);
              return Result.ok("文件导入成功！数据行数:" + listWechatTemplates.size());
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
