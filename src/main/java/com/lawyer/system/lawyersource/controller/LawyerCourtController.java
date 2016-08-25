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
import com.lawyer.system.lawyersource.domain.LawyerCourt;
import com.lawyer.system.lawyersource.service.LawyerCourtService;

@Controller
@RequestMapping(value = "/lawyerCourt")
public class LawyerCourtController {
	
	private static Logger logger = LoggerFactory.getLogger(LawyerCourtController.class);
	
	@Autowired
	private LawyerCourtService lawyerCourtService;

	
	/**
	 * <p>跳转到添加页面</p>
	 */
//	@RequiresPermissions("lawyerCourt:add")
	@RequestMappingName(value = "跳转到添加页面")
	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAdd(Model model) {
		return "com/lawyer/system/lawyersource/lawyerCourtAdd";
	}
	
	/**
	 * <p>增加法院信息</p>
	 */
//	@RequiresPermissions("lawyerCourt:add")
	@RequestMappingName(value = "增加法院信息")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results add(@Valid  LawyerCourt lawyerCourt,HttpServletRequest request) {
		
		Results results = lawyerCourtService.add(lawyerCourt);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>删除法院信息</p>
	 * 
	 */
//	@RequiresPermissions("lawyerCourt:delete")
	@RequestMappingName(value = "删除法院信息")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Results delete(@PathVariable("id")Long id,HttpServletRequest request) {
		
		Results results = lawyerCourtService.deleteById(id);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>跳转到修改页面</p>
	 * 
	 */
//	@RequiresPermissions("lawyerCourt:modify")
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toModify/{id}", method = RequestMethod.GET)
	public String toModify(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
	
		Results results = lawyerCourtService.findById(id);
		
		model.addAttribute("lawyerCourt", results.getData());
		return "com/lawyer/system/lawyersource/lawyerCourtModify";
		
	}
	
	/**
	 * <p>修改法院信息</p>
	 * 
	 */
//	@RequiresPermissions("lawyerCourt:modify")
	@RequestMappingName(value = "修改法院信息")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Results modify(@Valid  LawyerCourt lawyerCourt,HttpServletRequest request) {
		
		Results results = lawyerCourtService.modify(lawyerCourt);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

	
	/**
	 * <p>跳转到列表页面</p>
	 */
//	@RequiresPermissions("lawyerCourt:search")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toSearch", method = RequestMethod.GET)
	public String toSearch(Model model) {
		return "com/lawyer/system/lawyersource/lawyerCourt";
	}
	
	/**
	 * 查询法院信息
	 */
//	@RequiresPermissions("lawyerCourt:search")
	@RequestMappingName(value = "查询法院信息")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public Results search(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Results results = lawyerCourtService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}
	
	@RequestMappingName(value = "查询法院信息")
	@RequestMapping(value = "find/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Results findCourts(@PathVariable("id")Long id,
			HttpServletRequest request,HttpServletResponse response){
		Results results = new Results();
		
		if(id == 0){
			results = lawyerCourtService.findProvinces();
		}else{
			results = lawyerCourtService.findCourtsByPid(id);
		}
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}
	
	

}
