package com.lawyer.system.lawyersource.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.lawyer.cores.framework.mybatis.PageParameter;
import com.lawyer.cores.framework.spring.RequestMappingName;
import com.lawyer.cores.result.Results;
import com.lawyer.cores.tools.jackson.JacksonUtil;
import com.lawyer.system.lawyersource.domain.SysLawyerSource;
import com.lawyer.system.lawyersource.service.SysLawyerSourceService;

@Controller
@RequestMapping(value = "/sysLawyerSource")
public class SysLawyerSourceController {
	
	private static Logger logger = LoggerFactory.getLogger(SysLawyerSourceController.class);
	
	@Autowired
	private SysLawyerSourceService sysLawyerSourceService;

	
	/**
	 * <p>跳转到添加页面</p>
	 */
//	@RequiresPermissions("sysLawyerSource:add")
	@RequestMappingName(value = "跳转到添加页面")
	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAdd(Model model) {
		return "com/lawyer/system/lawyersource/sysLawyerSourceAdd";
	}
	
	/**
	 * <p>增加案源信息</p>
	 */
//	@RequiresPermissions("sysLawyerSource:add")
	@RequestMappingName(value = "增加案源信息")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results add(@Valid  SysLawyerSource sysLawyerSource,HttpServletRequest request) {
		
		Results results = sysLawyerSourceService.add(sysLawyerSource);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>删除案源信息</p>
	 * 
	 */
//	@RequiresPermissions("sysLawyerSource:delete")
	@RequestMappingName(value = "删除案源信息")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Results delete(@PathVariable("id")Long id,HttpServletRequest request) {
		
		Results results = sysLawyerSourceService.deleteById(id);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>跳转到修改页面</p>
	 * 
	 */
//	@RequiresPermissions("sysLawyerSource:modify")
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toModify/{id}", method = RequestMethod.GET)
	public String toModify(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
	
		Results results = sysLawyerSourceService.findById(id);
		
		model.addAttribute("sysLawyerSource", results.getData());
		return "com/lawyer/system/lawyersource/sysLawyerSourceModify";
		
	}
	
	/**
	 * <p>修改案源信息</p>
	 * 
	 */
//	@RequiresPermissions("sysLawyerSource:modify")
	@RequestMappingName(value = "修改案源信息")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Results modify(@Valid  SysLawyerSource sysLawyerSource,HttpServletRequest request) {
		
		Results results = sysLawyerSourceService.modify(sysLawyerSource);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

	
	/**
	 * <p>跳转到列表页面</p>
	 */
//	@RequiresPermissions("sysLawyerSource:search")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toSearch", method = RequestMethod.GET)
	public String toSearch(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response,Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Results results = sysLawyerSourceService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		model.addAttribute("result", results);
		
		return "com/lawyer/system/lawyersource/sysLawyerSource";
	}
	
	/**
	 * 查询案源信息
	 */
//	@RequiresPermissions("sysLawyerSource:search")
	@RequestMappingName(value = "查询案源信息")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public Results search(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Results results = sysLawyerSourceService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}
	
	/**
	 * <p>跳转到详情页面</p>
	 * 
	 */
//	@RequiresPermissions("sysLawyerSource:show")
	@RequestMappingName(value = "跳转到展示页面")
	@RequestMapping(value = "toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
	
		Results results = sysLawyerSourceService.showInfo(id);
		
		logger.info(new JacksonUtil().getJson(results));
		model.addAttribute("result", results.getData());
		return "com/lawyer/system/lawyersource/sysLawyerSourceShow";
		
	}

}
