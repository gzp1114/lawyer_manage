package com.lawyer.system.lawyersource.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.lawyer.system.lawyersource.domain.SysClaimCompany;
import com.lawyer.system.lawyersource.service.SysClaimCompanyService;

@Controller
@RequestMapping(value = "/sysClaimCompany")
public class SysClaimCompanyController {
	
	private static Logger logger = LoggerFactory.getLogger(SysClaimCompanyController.class);
	
	@Autowired
	private SysClaimCompanyService sysClaimCompanyService;

	
	/**
	 * <p>跳转到添加页面</p>
	 */
//	@RequiresPermissions("sysClaimCompany:add")
	@RequestMappingName(value = "跳转到添加页面")
	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAdd(Model model) {
		return "com/lawyer/system/lawyersource/sysClaimCompanyAdd";
	}
	
	/**
	 * <p>增加债权人信息</p>
	 */
//	@RequiresPermissions("sysClaimCompany:add")
	@RequestMappingName(value = "增加债权人信息")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results add(@Valid  SysClaimCompany sysClaimCompany,HttpServletRequest request) {
		
		String debtorCompanyId = request.getParameter("debtorid");
		Results results = sysClaimCompanyService.add(sysClaimCompany,debtorCompanyId);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>删除债权人信息</p>
	 * 
	 */
//	@RequiresPermissions("sysClaimCompany:delete")
	@RequestMappingName(value = "删除债权人信息")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Results delete(@PathVariable("id")Long id,HttpServletRequest request) {
		
		Results results = sysClaimCompanyService.deleteById(id);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>跳转到修改页面</p>
	 * 
	 */
//	@RequiresPermissions("sysClaimCompany:modify")
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toModify/{id}", method = RequestMethod.GET)
	public String toModify(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
	
		Results results = sysClaimCompanyService.findById(id);
		
		model.addAttribute("sysClaimCompany", results.getData());
		return "com/lawyer/system/lawyersource/sysClaimCompanyModify";
		
	}
	
	/**
	 * <p>修改债权人信息</p>
	 * 
	 */
//	@RequiresPermissions("sysClaimCompany:modify")
	@RequestMappingName(value = "修改债权人信息")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Results modify(@Valid  SysClaimCompany sysClaimCompany,HttpServletRequest request) {
		
		Results results = sysClaimCompanyService.modify(sysClaimCompany);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

	
	/**
	 * <p>跳转到列表页面</p>
	 */
//	@RequiresPermissions("sysClaimCompany:search")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toSearch", method = RequestMethod.GET)
	public String toSearch(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response,Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Results results = sysClaimCompanyService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		model.addAttribute("data", results);
		
		return "com/lawyer/system/lawyersource/sysClaimCompany";
	}
	
	/**
	 * 查询债权人信息
	 */
//	@RequiresPermissions("sysClaimCompany:search")
	@RequestMappingName(value = "查询债权人信息")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public Results search(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Results results = sysClaimCompanyService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

}
