
package com.lawyer.system.usercenter.controller;

import java.util.Date;
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
import com.lawyer.cores.utils.MD5;
import com.lawyer.system.usercenter.domain.SysUserSession;
import com.lawyer.system.usercenter.service.SysUserRoleService;
import com.lawyer.system.usercenter.service.SysUserSessionService;
import com.lawyer.system.usercenter.service.UserCenterContents;


@Controller
@RequestMapping(value = "/sysUser")
public class sysUserController {
	
	private static Logger logger = LoggerFactory.getLogger(sysUserController.class);
	
	@Autowired
	private SysUserSessionService sysUserSessionService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	
	/**
	 * <p>跳转到列表页面</p>
	 */
	@RequiresPermissions("sysUser:search")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toSearch", method = RequestMethod.GET)
	public String toSearch(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") Integer rows,
			HttpServletRequest request,HttpServletResponse response,Model model) {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Results results = sysUserSessionService.searchDtosByPage(searchParams,page,rows);
		model.addAttribute("res", results);

		logger.info(new JacksonUtil().getJson(results.getData()));
		return "com/lawyer/system/usercenter/sysUserInfo";
	}
	
	/**
	 * <p>跳转到添加页面</p>
	 */
	@RequiresPermissions("sysUser:add")
	@RequestMappingName(value = "跳转到添加页面")
	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAdd(Model model) {
		return "com/lawyer/system/usercenter/sysUserInfoAdd";
	}
	
	/**
	 * <p>跳转到修改页面</p>
	 * 
	 */
	@RequiresPermissions("sysUser:modify")
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toModify/{id}", method = RequestMethod.GET)
	public String toModify(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
		
		Results results = sysUserSessionService.findById(id);
		
		model.addAttribute("sysUser", results.getData());
		return "com/lawyer/system/usercenter/sysUserInfoModify";
	}
	
	/**
	 * <p>跳转到用户信息页面</p>
	 * 
	 */
	@RequiresPermissions("sysUser:modify")
	@RequestMappingName(value = "跳转到用户信息页面")
	@RequestMapping(value = "toShow", method = RequestMethod.GET)
	public String toShow(Model model,HttpServletRequest request) {
		
		SysUserSession userSession = (SysUserSession) request.getSession().getAttribute("sysUser");
		Results results = null;
		
		model.addAttribute("sysUser", results.getData());
		return "com/lawyer/system/usercenter/sysUserInfoShow";
	}
	
	/**
	 * <p>跳转到用户信息页面</p>
	 * 
	 */
	@RequiresPermissions("sysUser:modify")
	@RequestMappingName(value = "跳转到密码修改页面")
	@RequestMapping(value = "toPassword", method = RequestMethod.GET)
	public String toPassword(Model model,HttpServletRequest request) {
		
		return "com/lawyer/system/usercenter/sysModifyPassword";
	}
	
	/**
	 * <p>跳转到分配角色页面</p>
	 */
	@RequiresPermissions("sysUser:role")
	@RequestMappingName(value = "跳转到分配角色页面")
	@RequestMapping(value = "toRole/{id}", method = RequestMethod.GET)
	public String toRole(@PathVariable("id")Long id,Model model,HttpServletRequest request) {
		Results userRet = sysUserSessionService.findById(id);
		model.addAttribute("sysUser", userRet.getData());
		return "com/lawyer/system/usercenter/sysUserInfoRole";
	}
	
	/**
	 * <p>增加用户基本信息</p>
	 */
	@RequiresPermissions("sysUser:add")
	@RequestMappingName(value = "增加用户基本信息")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results add(@Valid SysUserSession sysUser,@RequestParam(value="roleids")String roleids,HttpServletRequest request) {
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		SysUserSession sysUserSession = (SysUserSession) request.getSession().getAttribute("sysUser");
		if(sysUserSession==null){
			results.setStatus(UserCenterContents.API_RETURN_STATUS.SESSION_TIMEOUT.value());
			results.setError("当前用户登录信息过期，请重新登录");
			return results;
		}
		
		sysUser.setLoginPassword(MD5.getMD5(sysUser.getLoginPassword()));
		sysUser.setCreateTime(new Date());
		sysUser.setStatus(1);
		sysUserSessionService.add(sysUser);
		
		if(roleids!=null&&!"".equals(roleids.trim())){
			sysUserRoleService.addMore(roleids,String.valueOf(sysUser.getId()));
		}
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * <p>修改用户信息</p>
	 * 
	 */
	@RequiresPermissions("sysUser:modify")
	@RequestMappingName(value = "修改用户信息")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Results modify(@Valid  SysUserSession sysUser,HttpServletRequest request) {
		
		Results results = null;
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * <p>删除用户基本信息</p>
	 * 
	 */
	@RequiresPermissions("sysUser:delete")
	@RequestMappingName(value = "删除用户基本信息")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Results delete(@PathVariable("id")Long id,HttpServletRequest request) {
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		try {
		} catch (Exception e) {
			results = new Results(
					UserCenterContents.API_RETURN_STATUS.SERVER_INTERNAL_ERROR.value(),
					UserCenterContents.API_RETURN_STATUS.SERVER_INTERNAL_ERROR.desc());
		}
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	
	/**
	 * 查询用户基本信息
	 */
	@RequiresPermissions("sysUser:search")
	@RequestMappingName(value = "查询用户基本信息")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public String search(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") Integer rows,
			HttpServletRequest request,HttpServletResponse response,Model model){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Results results = sysUserSessionService.searchDtosByPage(searchParams,page,rows);
		model.addAttribute("res", results);
		
		logger.info(new JacksonUtil().getJson(results));
		return "com/ariadnethread/uop/system/usercenter/sysUserInfo";
	}
	
	/**
	 * <p>给用户分配角色</p>
	 */
	@RequiresPermissions("sysUser:role")
	@RequestMappingName(value = "给用户分配角色")
	@RequestMapping(value = "role", method = RequestMethod.POST)
	@ResponseBody
	public Results role(HttpServletRequest request) {
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		String userInfoId = request.getParameter("userInfoId");
		if(StringUtils.isBlank(userInfoId)){
			results.setError("用户未选择");
			return results;
		}
		String roleids = request.getParameter("roleids");
		if(StringUtils.isBlank(roleids)){
			results.setError("用户角色未分配");
			return results;
		}
		
		results = sysUserRoleService.addMore(roleids,userInfoId);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * <p>修改用户密码</p>
	 * 
	 */
	@RequiresPermissions("sysUser:modify")
	@RequestMappingName(value = "修改用户密码")
	@RequestMapping(value = "password", method = RequestMethod.POST)
	@ResponseBody
	public Results password(HttpServletRequest request) {
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		if(password==null||"".equals(password)){
			results.setStatus(UserCenterContents.API_RETURN_STATUS.PARA_ERROR.value());
			results.setError("请输入密码。");
			return results;
		}
		if(!password2.endsWith(password)){
			results.setStatus(UserCenterContents.API_RETURN_STATUS.PARA_ERROR.value());
			results.setError("两次密码输入不一致，请重新输入");
			return results;
		}
		String userId = request.getParameter("userId");
		if(userId==null||"".equals(userId.trim())){
			results.setStatus(UserCenterContents.API_RETURN_STATUS.PARA_ERROR.value());
			results.setError("用户不存在。");
			return results;
		}
		password = MD5.getMD5(password);
		results = sysUserSessionService.findById(Long.parseLong(userId));
		SysUserSession sysUserSession = (SysUserSession) results.getData();
		if(sysUserSession==null){
			results.setStatus(UserCenterContents.API_RETURN_STATUS.PARA_ERROR.value());
			results.setError("用户不存在。");
			return results;
		}
		sysUserSession.setLoginPassword(password);
		
		results = sysUserSessionService.modify(sysUserSession);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * <p>获取全部用户信息</p>
	 */
	@RequestMappingName(value = "获取全部用户信息")
	@RequestMapping(value = "all", method = RequestMethod.POST)
	@ResponseBody
	public Results all(HttpServletRequest request) {
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		results = sysUserSessionService.findAll();
		return results;
	}
	
	
}
