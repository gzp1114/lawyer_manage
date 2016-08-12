package com.lawyer.system.usercenter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawyer.cores.framework.mybatis.PageParameter;
import com.lawyer.cores.result.Results;
import com.lawyer.system.usercenter.dao.mybatis.SysRoleMapper;
import com.lawyer.system.usercenter.dao.mybatis.SysRoleMenuMapper;
import com.lawyer.system.usercenter.dao.mybatis.SysUserRoleMapper;
import com.lawyer.system.usercenter.dao.mybatis.SysUserSessionMapper;
import com.lawyer.system.usercenter.domain.SysMenuFunction;
import com.lawyer.system.usercenter.domain.SysRole;
import com.lawyer.system.usercenter.domain.SysRoleMenu;
import com.lawyer.system.usercenter.domain.SysUserSession;

@Service("sysRoleService")
public class SysRoleService {
	
	private static Logger logger = LoggerFactory.getLogger(SysRoleService.class);
	
	@Autowired
	private SysRoleMapper sysRoleDao;
	@Autowired
	private SysUserSessionMapper sysUserSessionDao;
	@Autowired
	private SysUserRoleMapper sysUserRoleDao;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuDao;
	
	/**
	 * 获取角色的权限列表
	 * @param roleId
	 * @return
	 */
	public List<String> selectRolePermissionList(Long roleId) {
		List<String> permissions = new ArrayList<String>();
		
		SysRole sysRole = sysRoleDao.selectRoleMenuFunctions(roleId);
		if(sysRole!=null){
			for (SysMenuFunction menu : sysRole.getMenuFunctions()) {
				permissions.add(menu.getMenuUrl());
			}
		}
		return permissions;
	}

	
	/**
	 * 增加角色
	 * @param sysRole
	 * @return Results
	 */
	@Transactional
	public Results add(SysRole sysRole,String menuIdstr){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		if(sysRole.getCreateTime()==null){
			sysRole.setCreateTime(new Date());
		}
		//保存角色信息
		sysRoleDao.save(sysRole);
		Long roleId = sysRole.getId();
		
		//处理角色权限信息
		if(StringUtils.isBlank(menuIdstr) || StringUtils.isBlank(String.valueOf(roleId))){
			results.setError("角色添加成功"); 
			return results;
		}
		String[] menuIds = menuIdstr.split(",");
		
		List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
		for (String menuId : menuIds) {
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setRoleId( roleId);
			sysRoleMenu.setMenuFunctionId(Long.valueOf(menuId));
			sysRoleMenu.setCreateTime(new Date());
			
			list.add(sysRoleMenu);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();  
	    map.put("menulist", list); 
		sysRoleMenuDao.saveMore(map);
		
		results.setError("角色以及权限添加成功"); 
		return results; 
	}

	
	/**
	 * 删除角色
	 * @param Long id
	 * @return Results
	 */
	public Results deleteById(Long id){

		sysRoleDao.deleteById(id);
		//删除数据，该角色与用户的对应关系
		sysUserRoleDao.deleteByRole(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改角色
	 * @param sysRole
	 * @return Results
	 */
	public Results modify(SysRole sysRole,String menuIdstr){
		//更新角色信息
		sysRoleDao.update(sysRole);
		
		//处理角色权限信息
		List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
		if(!StringUtils.isBlank(menuIdstr)){
			String[] menuIds = menuIdstr.split(",");
			for (String menuId : menuIds) {
				SysRoleMenu sysRoleMenu = new SysRoleMenu();
				sysRoleMenu.setRoleId( sysRole.getId());
				sysRoleMenu.setMenuFunctionId(Long.valueOf(menuId));
				sysRoleMenu.setCreateTime(new Date());
				
				list.add(sysRoleMenu);
			}
		}
		
		sysRoleMenuDao.deleteByRole(sysRole.getId());
		if(list!=null&&list.size()>0){
			Map<String, Object> map = new HashMap<String, Object>();  
			map.put("menulist", list); 
			sysRoleMenuDao.saveMore(map);
		}
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询角色
	 * @param sysRole
	 * @return Results
	 */
	public Results findById(Long id){

		SysRole sysRole = sysRoleDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysRole);
		return results; 
	}
	
	/**
	 * 获取用户未拥有的角色
	 * @param sysRole
	 * @return Results
	 */
	public Results findUserRole(String userId){
		List<SysRole> all = sysRoleDao.findAll();
		if(userId!=null){
			SysUserSession userSession = sysUserSessionDao.findById(Long.valueOf(userId));
			
			List<SysRole> sysRoles = new ArrayList<SysRole>();
			
			if(userSession!=null){
				sysRoles = sysRoleDao.findUserRoles(userSession.getId());
			}
			
			for (SysRole allRole : all) {
				for (SysRole sysRole : sysRoles) {
					if(sysRole.getId() == allRole.getId()){
						allRole.setUserHaved(true);
					}
				}
			}
		}
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),all);
		return results; 
	}

	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return Results
	 */
	public Results searchByPage(Map<String,Object> searchParams,Integer pageNumber, Integer pageSize) {
		
		PageParameter page = new PageParameter(); 
		page.setCurrentPage(pageNumber); 
		page.setPageSize(pageSize); 
		searchParams.put("page", page); 

		List<SysRole> list = sysRoleDao.searchByPage(searchParams);
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("total", page.getTotalPage());
		rmap.put("page", page.getCurrentPage());
		rmap.put("records", page.getTotalCount());
		rmap.put("pageSize", page.getPageSize());
		rmap.put("rows", list);
		
		String roleName = searchParams.get("roleName")==null?"":String.valueOf(searchParams.get("roleName"));
		rmap.put("roleName", roleName); 
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),rmap);
		
		return results; 
	}

}