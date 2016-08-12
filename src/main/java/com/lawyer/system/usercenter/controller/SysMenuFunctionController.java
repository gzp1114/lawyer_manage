package com.lawyer.system.usercenter.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
import com.lawyer.system.usercenter.domain.SysMenuFunction;
import com.lawyer.system.usercenter.domain.SysUserSession;
import com.lawyer.system.usercenter.service.SysMenuFunctionService;
import com.lawyer.system.usercenter.service.UserCenterContents;

@Controller
@RequestMapping(value = "/sysMenuFunction")
public class SysMenuFunctionController {
	
	private static Logger logger = LoggerFactory.getLogger(SysMenuFunctionController.class);
	
	@Autowired
	private SysMenuFunctionService sysMenuFunctionService;
	
	/**
	 * <p>跳转到添加页面</p>
	 */
	@RequiresPermissions("sysMenuFunction:add")
	@RequestMappingName(value = "跳转到添加页面")
	@RequestMapping(value = "toAdd/{id}", method = RequestMethod.GET)
	public String toAdd(@PathVariable("id")Long id,Model model) {
		Results results = sysMenuFunctionService.findById(id);
		
		model.addAttribute("sysMenuFunction", results.getData());
		
		return "com/lawyer/system/usercenter/sysMenuFunctionAdd";
	}
	
	/**
	 * <p>增加菜单功能</p>
	 */
	@RequiresPermissions("sysMenuFunction:add")
	@RequestMappingName(value = "增加菜单功能")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results add(@Valid  SysMenuFunction sysMenuFunction,HttpServletRequest request) {
		
		//业务逻辑
		Results results = null;
		try{
			
			results =  sysMenuFunctionService.add(sysMenuFunction);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			results = new Results(
					UserCenterContents.API_RETURN_STATUS.SERVER_INTERNAL_ERROR.value(),
					UserCenterContents.API_RETURN_STATUS.SERVER_INTERNAL_ERROR.desc());
		}
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>删除菜单功能</p>
	 * 
	 */
	@RequiresPermissions("sysMenuFunction:delete")
	@RequestMappingName(value = "删除菜单功能，删除该菜单以及所有子菜单")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Results delete(@PathVariable("id")Long id,HttpServletRequest request) {
		
		Results results = sysMenuFunctionService.deleteById(id);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

	
	/**
	 * <p>跳转到修改页面</p>
	 * 
	 */
	@RequiresPermissions("sysMenuFunction:modify")
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toModify/{id}", method = RequestMethod.GET)
	public String toModify(@PathVariable("id")Long id,Model model,HttpServletRequest request) {

		Results results = sysMenuFunctionService.findById(id);
		SysMenuFunction menuFun = (SysMenuFunction) results.getData();
		SysMenuFunction parentMenuFun = new SysMenuFunction();
		if(menuFun!=null&&menuFun.getParentId()!=null&&menuFun.getParentId()>0){
			Object obj = sysMenuFunctionService.findById(menuFun.getParentId()).getData();
			if(obj!=null){
				parentMenuFun = (SysMenuFunction) obj;
			}
		}
		
		
		Results parentResults = sysMenuFunctionService.findById(id);

		model.addAttribute("sysMenuFunction", results.getData());
		model.addAttribute("parentMenuFunction", parentMenuFun);
		return "com/lawyer/system/usercenter/sysMenuFunctionModify";
		
	}
	
	/**
	 * <p>修改菜单功能</p>
	 * 
	 */
	@RequiresPermissions("sysMenuFunction:modify")
	@RequestMappingName(value = "修改菜单功能")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public Results modify(@Valid  SysMenuFunction sysMenuFunction,HttpServletRequest request) {
		
		Results results = sysMenuFunctionService.modify(sysMenuFunction);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}

	
	/**
	 * <p>跳转到列表页面</p>
	 */
	@RequiresPermissions("sysMenuFunction:search")
	@RequestMappingName(value = "跳转到列表页面")
	@RequestMapping(value = "toSearch", method = RequestMethod.GET)
	public String toSearch(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			@RequestParam(value = "search_parentId", defaultValue = "0") int parentId,
			HttpServletRequest request,HttpServletResponse response,Model model) {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		if(searchParams!=null){
			searchParams.put("parentId", parentId);
		}
		Results results = sysMenuFunctionService.searchByPage(searchParams,page,rows);
		model.addAttribute("res",results);
		
		return "com/lawyer/system/usercenter/sysMenuFunction";
	}
	
	/**
	 * 查询菜单功能
	 */
	@RequiresPermissions("sysMenuFunction:search")
	@RequestMappingName(value = "查询菜单功能")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public Results search(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int rows,
			HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Results results = sysMenuFunctionService.searchByPage(searchParams,page,rows);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}
	
	/**
	 * 查询菜单功能
	 */
	@RequiresPermissions("sysMenuFunction:search")
	@RequestMappingName(value = "检查menuUrl是否存在")
	@RequestMapping(value = "checkMenuUrl", method = RequestMethod.POST)
	@ResponseBody
	public Results checkMenuUrl(HttpServletRequest request){
		Results results = new Results(UserCenterContents.API_RETURN_STATUS.NORMAL.value(), UserCenterContents.API_RETURN_STATUS.NORMAL.value());
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Long existCount = sysMenuFunctionService.checkMenuUrl(searchParams);
		if(existCount>0){
			results = new Results(UserCenterContents.API_RETURN_STATUS.PARA_ERROR.value(), "URL已存在，请重新输入。");
		}
		logger.info(new JacksonUtil().getJson(results));
		return results;
		
	}
	
	/**
	 * <p>获取菜单树功能</p>
	 * 
	 */
	@RequestMappingName(value = "获取菜单树功能")
	@RequestMapping(value = "menutree", method = RequestMethod.POST)
	@ResponseBody
	public Results menutree(HttpServletRequest request) {
		Results results = new Results(UserCenterContents.API_RETURN_STATUS.NORMAL.value(),UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		
		SysUserSession userSession = (SysUserSession) request.getSession().getAttribute("sysUser");
		String result = "#";
		if(userSession == null){
			Subject currentUser = SecurityUtils.getSubject();  
    		currentUser.logout();
    		
    		result = "/toLogin";
    		results.setStatus(UserCenterContents.API_RETURN_STATUS.SESSION_TIMEOUT.value());
    		results.setError(UserCenterContents.API_RETURN_STATUS.SESSION_TIMEOUT.desc());
    		results.setData(result);
    		return results;
		}
		results = sysMenuFunctionService.getMenuTree(userSession.getId());
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * <p>获取菜单权限树功能</p>
	 * 
	 */
	@RequestMappingName(value = "获取菜单权限树功能")
	@RequestMapping(value = "allTree", method = RequestMethod.POST)
	@ResponseBody
	public Results allTree(HttpServletRequest request) {
		
		Results results = sysMenuFunctionService.getAllTree();
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}
	
	/**
	 * <p>获取菜单权限树功能</p>
	 * 
	 */
	@RequestMappingName(value = "获取菜单权限树功能")
	@RequestMapping(value = "roleTree/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Results roleTree(@PathVariable("id")Long roleid,HttpServletRequest request) {
		
		Results results = sysMenuFunctionService.getRoleTree(roleid);
		
		logger.info(new JacksonUtil().getJson(results));
		return results;
	}

}
