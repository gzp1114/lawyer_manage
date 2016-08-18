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
import com.lawyer.system.lawyersource.domain.SysContectEmail;
import com.lawyer.system.lawyersource.service.SysContectEmailService;

@Controller
@RequestMapping(value = "/sysContectEmail")
public class SysContectEmailController {
	
	private static Logger logger = LoggerFactory.getLogger(SysContectEmailController.class);
	
	@Autowired
	private SysContectEmailService sysContectEmailService;

	
	/**
	 * <p>跳转到添加页面</p>
	 */
//	@RequiresPermissions("sysContectEmail:add")
	@RequestMappingName(value = "跳转到添加页面")
	@RequestMapping(value = "toAdd/{lawyerSourceId}", method = RequestMethod.GET)
	public String toAdd(@PathVariable("lawyerSourceId")Long lawyerSourceId,HttpServletRequest request,Model model) {
		model.addAttribute("lawyerSourceId", lawyerSourceId);
		return "com/lawyer/system/lawyersource/sysContectEmailAdd";
	}
	
	/**
	 * <p>增加联系邮件</p>
	 */
//	@RequiresPermissions("sysContectEmail:add")
	@RequestMappingName(value = "增加联系邮件")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results add(@Valid  SysContectEmail sysContectEmail,HttpServletRequest request) {
		
		Results results = sysContectEmailService.add(sysContectEmail);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>删除联系邮件</p>
	 * 
	 */
//	@RequiresPermissions("sysContectEmail:delete")
	@RequestMappingName(value = "删除联系邮件")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Results delete(@PathVariable("id")Long id,HttpServletRequest request) {
		
		Results results = sysContectEmailService.deleteById(id);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>跳转到修改页面</p>
	 * 
	 */
//	@RequiresPermissions("sysContectEmail:modify")
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toModify/{id}", method = RequestMethod.GET)
	public String toModify(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
	
		Results results = sysContectEmailService.findById(id);
		
		model.addAttribute("sysContectEmail", results.getData());
		return "com/lawyer/system/lawyersource/sysContectEmailModify";
		
	}
	
	/**
	 * <p>修改联系邮件</p>
	 * 
	 */
//	@RequiresPermissions("sysContectEmail:modify")
	@RequestMappingName(value = "修改联系邮件")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Results modify(@Valid  SysContectEmail sysContectEmail,HttpServletRequest request) {
		
		Results results = sysContectEmailService.modify(sysContectEmail);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

	
	/**
	 * <p>跳转到列表页面</p>
	 */
//	@RequiresPermissions("sysContectEmail:search")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toSearch", method = RequestMethod.GET)
	public String toSearch(Model model) {
		return "com/lawyer/system/lawyersource/sysContectEmail";
	}
	
	/**
	 * 查询联系邮件
	 */
//	@RequiresPermissions("sysContectEmail:search")
	@RequestMappingName(value = "查询联系邮件")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public Results search(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Results results = sysContectEmailService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

}
