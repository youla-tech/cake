package com.cake.modules.cms.controller;

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
import com.cake.modules.cms.entity.Article;
import com.cake.modules.cms.service.IArticleService;
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
 * @Description: Article
 * @Author: eamiear
 * @Date:   2020-03-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="Article")
@RestController
@RequestMapping("/cms/article")
public class ArticleController {
	@Autowired
	private IArticleService articleService;

	/**
	 * 分页列表查询
	 * @param article article实体类
	 * @param pageNo    页码
	 * @param pageSize  每页条数
	 * @param req       request
	 * @return
	 */
	@AutoLog(value = "Article-分页列表查询")
	@ApiOperation(value="Article-分页列表查询", notes="Article-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Article>> queryPageList(Article article,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Article>> result = new Result<IPage<Article>>();
		QueryWrapper<Article> queryWrapper = QueryGenerator.initQueryWrapper(article, req.getParameterMap());
		Page<Article> page = new Page<Article>(pageNo, pageSize);
		IPage<Article> pageList = articleService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * @param article article实体类
	 * @return
	 */
	@AutoLog(value = "Article-添加")
	@ApiOperation(value="Article-添加", notes="Article-添加")
	@PostMapping(value = "/add")
	public Result<Article> add(@RequestBody Article article) {
		Result<Article> result = new Result<Article>();
		try {
			articleService.save(article);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	 * 编辑
	 * @param article article实体类
	 * @return
	 */
	@AutoLog(value = "Article-编辑")
	@ApiOperation(value="Article-编辑", notes="Article-编辑")
	@PutMapping(value = "/edit")
	public Result<Article> edit(@RequestBody Article article) {
		Result<Article> result = new Result<Article>();
		Article articleEntity = articleService.getById(article.getId());
		if(articleEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = articleService.updateById(article);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}

	/**
	 * 通过id删除
	 * @param id article id
	 * @return
	 */
	@AutoLog(value = "Article-通过id删除")
	@ApiOperation(value="Article-通过id删除", notes="Article-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			articleService.removeById(id);
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
	@AutoLog(value = "Article-批量删除")
	@ApiOperation(value="Article-批量删除", notes="Article-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Article> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Article> result = new Result<Article>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.articleService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	 * 通过id查询
	 * @param id article id
	 * @return
	 */
	@AutoLog(value = "Article-通过id查询")
	@ApiOperation(value="Article-通过id查询", notes="Article-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Article> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Article> result = new Result<Article>();
		Article article = articleService.getById(id);
		if(article==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(article);
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
      QueryWrapper<Article> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Article article = JSON.parseObject(deString, Article.class);
              queryWrapper = QueryGenerator.initQueryWrapper(article, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Article> pageList = articleService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "Article列表");
      mv.addObject(NormalExcelConstants.CLASS, Article.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("Article列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Article> listArticles = ExcelImportUtil.importExcel(file.getInputStream(), Article.class, params);
              articleService.saveBatch(listArticles);
              return Result.ok("文件导入成功！数据行数:" + listArticles.size());
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
