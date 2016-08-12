package com.lawyer.system.usercenter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawyer.cores.result.Results;
import com.lawyer.system.usercenter.dao.mybatis.SysUserRoleMapper;
import com.lawyer.system.usercenter.dao.mybatis.SysUserSessionMapper;
import com.lawyer.system.usercenter.domain.SysUserRole;
import com.lawyer.system.usercenter.domain.SysUserSession;

@Service("sysUserRoleService")
public class SysUserRoleService {
	
	private static Logger logger = LoggerFactory.getLogger(SysUserRoleService.class);
	
	@Autowired
	private SysUserRoleMapper sysUserRoleDao;
	@Autowired
	private SysUserSessionMapper sysUserSessionDao;

	
	/**
	 * 增加用户角色关系
	 * @param sysUserRole
	 * @return Results
	 */
	public Results addMore(String roleIdStr,String userId){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		String[] roleIds = roleIdStr.split(",");
		SysUserSession userSession = sysUserSessionDao.findById(Long.valueOf(userId));
		if(userSession == null){
			results.setStatus("1");
			results.setError("获取用户状态信息失败");
			return results; 
		}
		
		List<SysUserRole> list = new ArrayList<>();
		for (String roleid : roleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserSessionId(userSession.getId());
			sysUserRole.setRoleId(Long.valueOf(roleid));
			sysUserRole.setCreateTime(new Date());
			
			list.add(sysUserRole);
		}
		
		sysUserRoleDao.deleteByUser(userSession.getId());
		sysUserRoleDao.saveMore(list);
		
		results.setError("用户分配角色成功");
		return results; 
	}
	
	/**
	 * 增加用户角色关系,新建用户时
	 * @param sysUserRole
	 * @return Results
	 */
	public void addMore(String roleIdStr, SysUserSession userSession, SysUserSession currentUser){
		if(userSession == null||currentUser==null||roleIdStr==null||"".equals(roleIdStr.trim())){
			return;
		}
		String[] roleIds = roleIdStr.split(",");
		List<SysUserRole> list = new ArrayList<>();
		for (String roleid : roleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserSessionId(userSession.getId());
			sysUserRole.setRoleId(Long.valueOf(roleid));
			sysUserRole.setCreateTime(new Date());
			
			list.add(sysUserRole);
		}
		sysUserRoleDao.saveMore(list);
	}

	
	/**
	 * 删除用户角色关系
	 * @param Long id
	 * @return Results
	 */
	public Results deleteById(Long id){

		sysUserRoleDao.deleteById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改用户角色关系
	 * @param sysUserRole
	 * @return Results
	 */
	public Results modify(SysUserRole sysUserRole){

		sysUserRoleDao.update(sysUserRole);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询用户角色关系
	 * @param sysUserRole
	 * @return Results
	 */
	public Results findById(Long id){

		SysUserRole sysUserRole = sysUserRoleDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysUserRole);
		return results; 
	}

}
