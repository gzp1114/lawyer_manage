package com.lawyer.system.usercenter.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.usercenter.domain.SysRole;

@MyBatisRepository
public interface SysRoleMapper {

	/**
	 * save sys_role
	 * 
	 * @param SysRole
	 * @return
	 */
	public void save(SysRole sysRole);

	/**
	 * update sys_role
	 * 
	 * @param SysRole
	 * @return
	 */
	public void update(SysRole sysRole);

	/**
	 * query sys_role by primary key 
	 * @param Long id
	 * @return SysRole
	 */
	 public SysRole findById(Long id);
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<SysRole> searchByPage(Map<String,Object> searchParams);
	 
	 /**
	 * delete sys_role by primary key 
	 * @param SysRole
	 * @return 
	 */
	 public void deleteById(Long id);
	
	/**
	 * 根据角色id获取菜单权限
	 * @param roleId
	 * @return
	 */
	public SysRole selectRoleMenuFunctions(Long roleId);
	
	/**
	 * 获取所有的角色
	 */
	 public  List<SysRole> findAll();
	 
	 /**
	 * 获取用户未拥有的角色
	 */
	 public  List<SysRole> findUserNo(Long userSessionId);
	 
	 /**
	 * 获取用户拥有的角色
	 */
	 public  List<SysRole> findUserRoles(Long userSessionId);
	 

}
