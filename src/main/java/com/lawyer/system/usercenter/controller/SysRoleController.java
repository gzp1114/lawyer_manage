package com.lawyer.system.usercenter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import com.lawyer.system.usercenter.domain.SysRole;
import com.lawyer.system.usercenter.service.SysRoleService;
import com.lawyer.system.usercenter.service.UserCenterContents;

@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleController {
	
	private static Logger logger = LoggerFactory.getLogger(SysRoleController.class);
	
	@Autowired
	private SysRoleService sysRoleService;

	
	/**
	 * <p>跳转到添加页面</p>
	 */
	@RequiresPermissions("sysRole:add")
	@RequestMappingName(value = "跳转到添加页面")
	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAdd(Model model) {
		return "com/lawyer/system/usercenter/sysRoleAdd";
	}
	
	/**
	 * <p>增加角色</p>
	 */
	@RequiresPermissions("sysRole:add")
	@RequestMappingName(value = "增加角色")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results add(@Valid  SysRole sysRole,HttpServletRequest request) {
		Results results = new Results();
		
		String menuIds = request.getParameter("menuids");
		results = sysRoleService.add(sysRole,menuIds);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>删除角色</p>
	 * 
	 */
	@RequiresPermissions("sysRole:delete")
	@RequestMappingName(value = "删除角色")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Results delete(@PathVariable("id")Long id,HttpServletRequest request) {
		
		Results results = sysRoleService.deleteById(id);
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>跳转到修改页面</p>
	 * 
	 */
	@RequiresPermissions("sysRole:modify")
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toModify/{id}", method = RequestMethod.GET)
	public String toModify(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
	
		Results results = sysRoleService.findById(id);
		
		model.addAttribute("sysRole", results.getData());
		return "com/lawyer/system/usercenter/sysRoleModify";
		
	}
	
	/**
	 * <p>修改角色</p>
	 * 
	 */
	@RequiresPermissions("sysRole:modify")
	@RequestMappingName(value = "修改角色")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Results modify(@Valid  SysRole sysRole,HttpServletRequest request) {
		
		String menuIds = request.getParameter("menuids");
		Results results = sysRoleService.modify(sysRole,menuIds);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

	
	/**
	 * <p>跳转到列表页面</p>
	 */
	@RequiresPermissions("sysRole:search")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toSearch", method = RequestMethod.GET)
	public String toSearch(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response,Model model) {
        
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Results results = sysRoleService.searchByPage(searchParams,page,rows);
		
		model.addAttribute("data", results);
		logger.info(new JacksonUtil().getJson(results));
		return "com/lawyer/system/usercenter/sysRole";
	}
	/**
	 * 查询角色
	 */
	@RequiresPermissions("sysRole:search")
	@RequestMappingName(value = "查询角色")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response,Model model){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Results results = sysRoleService.searchByPage(searchParams,page,rows);
		logger.info(new JacksonUtil().getJson(results));
		model.addAttribute("data", results);
		return "com/lawyer/system/usercenter/sysRole";
		
	}
	
	/**
	 * 查询用户未拥有角色
	 */
	@RequestMappingName(value = "获取用户未拥有角色")
	@RequestMapping(value = "userRole", method = RequestMethod.POST)
	@ResponseBody
	public Results userNo(HttpServletRequest request,HttpServletResponse response){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		String userInfoId = request.getParameter("userInfoId");
		if(StringUtils.isBlank(userInfoId)){
			results.setStatus(UserCenterContents.API_RETURN_STATUS.PARA_ERROR.value());
			results.setError("用户未选择");
			return results;
		}
		
		results = sysRoleService.findUserRole(userInfoId);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}
	
	/**
	 * 查询用户未拥有角色
	 */
	@RequestMappingName(value = "获取用户未拥有角色")
	@RequestMapping(value = "allRole", method = RequestMethod.POST)
	@ResponseBody
	public Results allRole(HttpServletRequest request,HttpServletResponse response){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		results = sysRoleService.findUserRole(null);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

}