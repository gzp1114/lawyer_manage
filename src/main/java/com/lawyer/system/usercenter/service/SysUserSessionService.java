package com.lawyer.system.usercenter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawyer.cores.framework.mybatis.PageParameter;
import com.lawyer.cores.result.Results;
import com.lawyer.cores.utils.MD5;
import com.lawyer.system.usercenter.dao.mybatis.SysUserSessionMapper;
import com.lawyer.system.usercenter.domain.ShiroUser;
import com.lawyer.system.usercenter.domain.SysUserSession;
import com.lawyer.system.usercenter.shiro.ShiroDbRealm;

@Service("sysUserSessionService")
public class SysUserSessionService {
	
	private static Logger logger = LoggerFactory.getLogger(SysUserRoleService.class);

	@Autowired
	private SysUserSessionMapper sysUserSessionDao;
	@Autowired
	private ShiroDbRealm shiroRealm;
	
	/**
	 * 增加用户基本信息
	 * @param sysUserInfo
	 * @return Results
	 */
	public Results add(SysUserSession sysUserSession){
		String error = null;
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		if (sysUserSessionDao.findByLoginAccount(sysUserSession.getLoginAccount()) != null) {
			error = "用户添加失败，登录名：" + sysUserSession.getLoginAccount() + "已存在。";
			results.setData(error);
		}
		
		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		if (StringUtils.isNotBlank(sysUserSession.getLoginPassword())) {
			sysUserSession.setLoginPassword(MD5.getMD5(sysUserSession.getLoginPassword()));
		}
		
		sysUserSessionDao.save(sysUserSession);
		shiroRealm.clearCachedAuthorizationInfo(sysUserSession.getLoginAccount());
		
		return results; 
	}
	
	/**
	 * 删除用户基本信息
	 * @param Long id
	 * @return Results
	 */
	public Results delete(Long id){

		//TODU
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}
	
	/**
	 * 修改用户基本信息
	 * @param sysUserInfo
	 * @return Results
	 */
	public Results modify(SysUserSession sysUserSession){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		try {
			//修改数据库
			sysUserSessionDao.update(sysUserSession);
			
		} catch (Exception e) {
			logger.error("用户"+sysUserSession.getLoginAccount()+"修改密码异常："+e.getMessage());
			results.setStatus(UserCenterContents.API_RETURN_STATUS.SERVER_INTERNAL_ERROR.value());
			results.setError("修改密码失败，具体情况请联系管理员");
		}
		
		
		return results; 
	}
	
	/**
	 * 按id查询记录
	 * @param  Long id
	 * @return Results
	 */
	public Results findById(Long id){
		
		SysUserSession sysUserSession = new SysUserSession();
		sysUserSession = sysUserSessionDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysUserSession);
		return results; 
	}
	
	/**
	 * 按登陆名称查询记录
	 * @param  Long id
	 * @return Results
	 */
	public Results findByLoginAccount(String loginAccount) {
		SysUserSession sysUserSession = sysUserSessionDao.findByLoginAccount(loginAccount);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysUserSession);
		return results; 
	}
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return Results
	 */
	public Results searchByPage(Map<String,Object> searchParams) {

		//TODU
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("total", 1);
		rmap.put("page", 1);
		rmap.put("records", 10);
		
		List<SysUserSession> list = new ArrayList<SysUserSession>();
		rmap.put("rows", list);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		return results; 
	}
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams	条件
	 * @param pageNumber	页码
	 * @param pageSize		每页数据大小
	 * @return Results
	 */
	public Results searchDtosByPage(Map<String,Object> searchParams,Integer pageNumber, Integer pageSize) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		
		PageParameter page = new PageParameter();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		searchParams.put("page", page);
		searchParams.put("status", new Integer(1));

		List<SysUserSession> list = sysUserSessionDao.searchByPage(searchParams);
		
		rmap.put("total", page.getTotalPage());
		rmap.put("page", pageNumber);   			      //当前页
		rmap.put("records", page.getTotalCount()); //总记录数
		rmap.put("pageSize", pageSize);				      //每页个数
		
		String userName = searchParams.get("username")==null?"":String.valueOf(searchParams.get("username"));
		String phone = searchParams.get("phone")==null?"":String.valueOf(searchParams.get("phone"));
		rmap.put("username", userName);  
		rmap.put("phone", phone);
		
		rmap.put("rows", list);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),rmap);
		
		return results; 
	}
	
	/**
	 * 按登陆名称查询用户信息
	 * @param  Long id
	 * @return Results
	 */
	public SysUserSession selectUserRoles(String loginAccount){
		return sysUserSessionDao.selectUserRoles(loginAccount);
	}
	
	
	public Results updateLoginMessage(Subject currentUser,String  loginIp){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		SysUserSession sysUserSession = null;
		Object obj = currentUser.getPrincipal();
		if(obj instanceof ShiroUser){
			sysUserSession = ((ShiroUser) obj).getUser();
		}else if(obj instanceof String){
			Results result = findByLoginAccount((String) obj);
			sysUserSession = (SysUserSession) result.getData();
		}
		
		if(sysUserSession == null){
			return results;
		}
		
		sysUserSession.setLastLoginTime(new Date());
		sysUserSession.setLoginIp(loginIp);
		sysUserSession.setSessionId(currentUser.getSession().getId().toString());
		
		sysUserSessionDao.update(sysUserSession);
		results.setData(sysUserSession);
		return results; 
	} 
	
}
