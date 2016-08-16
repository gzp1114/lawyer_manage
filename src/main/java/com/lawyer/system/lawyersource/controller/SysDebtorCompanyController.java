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
import com.lawyer.system.lawyersource.domain.SysDebtorCompany;
import com.lawyer.system.lawyersource.service.SysDebtorCompanyService;

@Controller
@RequestMapping(value = "/sysDebtorCompany")
public class SysDebtorCompanyController {
	
	private static Logger logger = LoggerFactory.getLogger(SysDebtorCompanyController.class);
	
	@Autowired
	private SysDebtorCompanyService sysDebtorCompanyService;

	
	/**
	 * <p>跳转到添加页面</p>
	 */
//	@RequiresPermissions("sysDebtorCompany:add")
	@RequestMappingName(value = "跳转到添加页面")
	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAdd(Model model) {
		return "com/lawyer/system/lawyersource/sysDebtorCompanyAdd";
	}
	
	/**
	 * <p>增加债务人信息</p>
	 */
//	@RequiresPermissions("sysDebtorCompany:add")
	@RequestMappingName(value = "增加债务人信息")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results add(@Valid  SysDebtorCompany sysDebtorCompany,HttpServletRequest request) {
		
		Results results = sysDebtorCompanyService.add(sysDebtorCompany);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>删除债务人信息</p>
	 * 
	 */
//	@RequiresPermissions("sysDebtorCompany:delete")
	@RequestMappingName(value = "删除债务人信息")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Results delete(@PathVariable("id")Long id,HttpServletRequest request) {
		
		Results results = sysDebtorCompanyService.deleteById(id);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>跳转到修改页面</p>
	 * 
	 */
//	@RequiresPermissions("sysDebtorCompany:modify")
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toModify/{id}", method = RequestMethod.GET)
	public String toModify(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
	
		Results results = sysDebtorCompanyService.findById(id);
		
		model.addAttribute("sysDebtorCompany", results.getData());
		return "com/lawyer/system/lawyersource/sysDebtorCompanyModify";
		
	}
	
	/**
	 * <p>修改债务人信息</p>
	 * 
	 */
//	@RequiresPermissions("sysDebtorCompany:modify")
	@RequestMappingName(value = "修改债务人信息")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Results modify(@Valid  SysDebtorCompany sysDebtorCompany,HttpServletRequest request) {
		
		Results results = sysDebtorCompanyService.modify(sysDebtorCompany);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

	
	/**
	 * <p>跳转到列表页面</p>
	 */
//	@RequiresPermissions("sysDebtorCompany:search")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toSearch", method = RequestMethod.GET)
	public String toSearch(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response,Model model) {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Results results = sysDebtorCompanyService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		model.addAttribute("data", results);
		
		return "com/lawyer/system/lawyersource/sysDebtorCompany";
	}
	
	/**
	 * 查询债务人信息
	 */
//	@RequiresPermissions("sysDebtorCompany:search")
	@RequestMappingName(value = "查询债务人信息")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public String search(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response,Model model){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Results results = sysDebtorCompanyService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		model.addAttribute("data", results);
		return "com/lawyer/system/lawyersource/sysDebtorCompany";
	}
	
	/**
	 * <p>跳转到批量操作页面</p>
	 */
//	@RequiresPermissions("sysDebtorCompany:import")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toOperate", method = RequestMethod.GET)
	public String toOperate(Model model) {
		return "com/lawyer/system/lawyersource/sysDebtorCompanyOperate";
	}
	
	/**
	 * 批量导入被执行信息
	 */
//	@RequiresPermissions("sysDebtorCompany:import")
	@RequestMappingName(value = "批量导入被执行信息")
	@RequestMapping(value = "import/{type}", method = RequestMethod.GET)
	@ResponseBody
	public Results importDebtor(@PathVariable("type")Integer type,HttpServletRequest request){
		
		Results results = sysDebtorCompanyService.importDebtor(type,request);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * <p>跳转到详情页面</p>
	 * 
	 */
//	@RequiresPermissions("sysDebtorCompany:show")
	@RequestMappingName(value = "跳转到展示页面")
	@RequestMapping(value = "toShow/{id}", method = RequestMethod.GET)
	public String toShow(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
	
		Results results = sysDebtorCompanyService.showInfo(id);
		
		model.addAttribute("result", results.getData());
		return "com/lawyer/system/lawyersource/sysDebtorCompanyShow";
		
	}
	
	/**
	 * <p>模糊查询债务人信息</p>
	 * 
	 */
	@RequestMappingName(value = "根据名称模糊查询债务人信息")
	@RequestMapping(value = "select", method = RequestMethod.GET)
	@ResponseBody
	public Results select(HttpServletRequest request) {
		String name = request.getParameter("name");
		Results results = sysDebtorCompanyService.selectByName(name);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

}
