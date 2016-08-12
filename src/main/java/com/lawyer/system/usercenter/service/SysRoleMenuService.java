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
import com.lawyer.system.usercenter.dao.mybatis.SysRoleMenuMapper;
import com.lawyer.system.usercenter.domain.SysRole;
import com.lawyer.system.usercenter.domain.SysRoleMenu;

@Service("sysRoleMenuService")
public class SysRoleMenuService {
	
	private static Logger logger = LoggerFactory.getLogger(SysRoleMenuService.class);
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuDao;

	
	/**
	 * 增加角色菜单权限关系
	 * @param sysRoleMenu
	 * @return Results
	 */
	@Transactional
	public Results add(SysRoleMenu sysRoleMenu){
		
		sysRoleMenuDao.save(sysRoleMenu);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		return results; 
	}
	
	
	/**
	 * 删除角色菜单权限关系
	 * @param Long id
	 * @return Results
	 */
	@Transactional
	public Results deleteById(Long id){

		sysRoleMenuDao.deleteById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改角色菜单权限关系
	 * @param sysRoleMenu
	 * @return Results
	 */
	@Transactional
	public Results modify(SysRoleMenu sysRoleMenu){

		sysRoleMenuDao.update(sysRoleMenu);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询角色菜单权限关系
	 * @param sysRoleMenu
	 * @return Results
	 */
	public Results findById(Long id){

		SysRoleMenu sysRoleMenu = sysRoleMenuDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysRoleMenu);
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

		List<SysRoleMenu> list = sysRoleMenuDao.searchByPage(searchParams);
		
		Map rmap = new HashMap();
		rmap.put("total", page.getTotalPage());
		rmap.put("page", page.getCurrentPage());
		rmap.put("records", page.getTotalCount());
		
		rmap.put("rows", list);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),rmap);
		
		return results; 
	}

}
