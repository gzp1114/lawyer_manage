
package com.lawyer.system.login.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lawyer.cores.framework.spring.RequestMappingName;
import com.lawyer.cores.result.Results;
import com.lawyer.cores.tools.other.RemoteUtils;
import com.lawyer.cores.utils.MD5;
import com.lawyer.system.usercenter.dao.mybatis.SysUserSessionMapper;
import com.lawyer.system.usercenter.domain.SysUserSession;
import com.lawyer.system.usercenter.service.SysMenuFunctionService;
import com.lawyer.system.usercenter.service.SysRoleService;
import com.lawyer.system.usercenter.service.SysUserSessionService;
import com.lawyer.system.usercenter.service.UserCenterContents;


@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
    private SysUserSessionService userSessionService;
	@Autowired
    private SysMenuFunctionService sysMenuFunctionService;
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysUserSessionMapper sysUserSessionDao;
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	@RequestMappingName(value = "跳转到登陆页面")
	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return "/login/login";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Results login(HttpServletRequest request) {
		Results results = new Results(UserCenterContents.API_RETURN_STATUS.NORMAL.value(),UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		String result = "login";
		// 此处默认有值
		String username = request.getParameter("userName");
		//MD5加密
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username,MD5.getMD5(password));
		
		Subject currentUser = SecurityUtils.getSubject();
		try {
//			//登录验证
			SysUserSession sysUserSession = sysUserSessionDao.findByLoginAccount(username);
			if(sysUserSession==null){
				result = "/toLogin";
				results.setStatus(UserCenterContents.API_RETURN_STATUS.SERVER_INTERNAL_ERROR.value());
				results.setError("用户不存在，请联系管理员");
				results.setData(result);
				return results;
				
			}else if(!sysUserSession.getLoginPassword().equals(MD5.getMD5(password))){
				result = "/toLogin";
				results.setStatus(UserCenterContents.API_RETURN_STATUS.SERVER_INTERNAL_ERROR.value());
				results.setError("密码错误，请重新登录 ");
				results.setData(result);
				return results;
				
			}
			if (!currentUser.isAuthenticated()){
				token.setRememberMe(false);
				currentUser.login(token);
				currentUser.getSession().setTimeout(1800000);
			}
			//业务逻辑
			String ip = RemoteUtils.getRemoteHost(request);
			results = userSessionService.updateLoginMessage(currentUser, ip);
			SysUserSession sysuser=(SysUserSession) results.getData();
			
			//session中保存登录用户信息
			request.getSession().setAttribute("sysUser", sysuser);
			request.getSession().setAttribute("username", sysuser.getUsername());
			request.getSession().setAttribute("lastLoginTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(sysuser.getLastLoginTime()));
			
			results = sysMenuFunctionService.getMenuTree(sysuser.getId());
			request.getSession().setAttribute("menutree", results.getData());
			
			
			result = "/index/toIndex";
			results.setData(result);
		} catch (Exception e) {
			result = "/toLogin";
			
			logger.error(e.getMessage());
			results.setStatus(UserCenterContents.API_RETURN_STATUS.SERVER_INTERNAL_ERROR.value());
			results.setError("登录信息验证不通过，请联系管理员 ");
			results.setData(result);
			
			e.printStackTrace();
		}
		return results;
	}
	
	@RequestMapping("/session")
	@ResponseBody
	public Results session(HttpServletRequest request) {
		Results results = new Results(UserCenterContents.API_RETURN_STATUS.NORMAL.value(),UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		String result = "login";
		
		SysUserSession userSession = (SysUserSession) request.getSession().getAttribute("sysUser");
		if(userSession == null){
			Subject currentUser = SecurityUtils.getSubject();  
    		currentUser.logout();
    		
    		result = "/toLogin";
    		results.setStatus(UserCenterContents.API_RETURN_STATUS.SESSION_TIMEOUT.value());
    		results.setError(UserCenterContents.API_RETURN_STATUS.SESSION_TIMEOUT.desc());
    		results.setData(result);
    		return results;
		}
		
		results.setData(userSession);
		
		return results;
	}
  
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	@RequestMappingName(value = "跳转到登陆页面")
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String toLogout() {
		logger.info("logout.....");
    	String result = "/login/login";
		
    	try{
    		Subject currentUser = SecurityUtils.getSubject();  
    		currentUser.logout();  
    		
    	}catch(Exception e){
    		logger.error("用户注销登录异常："+e.getMessage());
    	}
    	logger.info("return logout.....");
		return result;
	}
	
    /**
     * 登出
     * @return
     */
//    @RequestMapping(value = "logout")  
//    @ResponseBody
//    public Results logout() {
//    	
//    	logger.info("logout.....");
//    	String result = "/toLogin";
//    	
//    	Results results = new Results(UserCenterContents.API_RETURN_STATUS.NORMAL.value(),UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),result);
//		
//    	try{
//    		Subject currentUser = SecurityUtils.getSubject();  
//    		currentUser.logout();  
//    		
//    		results.setData(result);
//    	}catch(Exception e){
//    		logger.error("用户注销登录异常："+e.getMessage());
//    		
//    		results.setStatus(UserCenterContents.API_RETURN_STATUS.SERVER_INTERNAL_ERROR.value());
//			results.setError("登出错误！");
//			
//    	}
//    	logger.info("return logout.....");
//        return results;
//    }  
    
}
