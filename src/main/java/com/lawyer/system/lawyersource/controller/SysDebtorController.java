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
import com.lawyer.system.lawyersource.domain.SysDebtor;
import com.lawyer.system.lawyersource.service.SysDebtorService;

@Controller
@RequestMapping(value = "/sysDebtor")
public class SysDebtorController {
	
	private static Logger logger = LoggerFactory.getLogger(SysDebtorController.class);
	
	@Autowired
	private SysDebtorService sysDebtorService;

	
	/**
	 * <p>跳转到添加页面</p>
	 */
//	@RequiresPermissions("sysDebtor:add")
	@RequestMappingName(value = "跳转到添加页面")
	@RequestMapping(value = "toAdd/{debtorCompanyId}", method = RequestMethod.GET)
	public String toAdd(@PathVariable("debtorCompanyId")Long debtorCompanyId,Model model) {
		model.addAttribute("debtorCompanyId", debtorCompanyId);
		
		return "com/lawyer/system/lawyersource/sysDebtorAdd";
	}
	
	/**
	 * <p>增加被执行信息</p>
	 */
//	@RequiresPermissions("sysDebtor:add")
	@RequestMappingName(value = "增加被执行信息")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results add(@Valid  SysDebtor sysDebtor,HttpServletRequest request) {
		
		Results results = sysDebtorService.add(sysDebtor);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>删除被执行信息</p>
	 * 
	 */
//	@RequiresPermissions("sysDebtor:delete")
	@RequestMappingName(value = "删除被执行信息")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Results delete(@PathVariable("id")Long id,HttpServletRequest request) {
		
		Results results = sysDebtorService.deleteById(id);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>跳转到修改页面</p>
	 * 
	 */
//	@RequiresPermissions("sysDebtor:modify")
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toModify/{id}", method = RequestMethod.GET)
	public String toModify(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
	
		Results results = sysDebtorService.findById(id);
		
		model.addAttribute("sysDebtor", results.getData());
		return "com/lawyer/system/lawyersource/sysDebtorModify";
		
	}
	
	/**
	 * <p>修改被执行信息</p>
	 * 
	 */
//	@RequiresPermissions("sysDebtor:modify")
	@RequestMappingName(value = "修改被执行信息")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Results modify(@Valid  SysDebtor sysDebtor,HttpServletRequest request) {
		
		Results results = sysDebtorService.modify(sysDebtor);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

	
	/**
	 * <p>跳转到列表页面</p>
	 */
//	@RequiresPermissions("sysDebtor:search")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toSearch", method = RequestMethod.GET)
	public String toSearch(Model model) {
		return "com/lawyer/system/lawyersource/sysDebtor";
	}
	
	/**
	 * 查询被执行信息
	 */
//	@RequiresPermissions("sysDebtor:search")
	@RequestMappingName(value = "查询被执行信息")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public Results search(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Results results = sysDebtorService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

}
