package com.lawyer.system.usercenter.dao.mybatis;

import java.util.List;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.usercenter.domain.SysUserRole;

@MyBatisRepository
public interface SysUserRoleMapper {

	/**
	 * save sys_user_role
	 * 
	 * @param SysUserRole
	 * @return
	 */
	public void save(SysUserRole sysUserRole);
	
	/**
	 * 批量插入sys_user_role
	 * @param list
	 */
	public void saveMore(List<SysUserRole> list);

	/**
	 * update sys_user_role
	 * 
	 * @param SysUserRole
	 * @return
	 */
	public void update(SysUserRole sysUserRole);

	/**
	 * query sys_user_role by primary key
	 * 
	 * @param Long
	 *            id
	 * @return SysUserRole
	 */
	public SysUserRole findById(Long id);
	
	/**
	 * query sys_user_role by primary key
	 * 
	 * @param Long userSessionId
	 * @return SysUserRole
	 */
	public List<SysUserRole> findByUserId(Long userSessionId);

	/**
	 * delete sys_user_role by primary key
	 * 
	 * @param SysUserRole
	 * @return
	 */
	public void deleteById(Long id);
	
	/**
	 * delete sys_user_role by userSessionId
	 * 
	 * @param SysUserRole
	 * @return
	 */
	public void deleteByUser(Long userSessionId);
	
	/**
	 * delete sys_user_role by roleId
	 * @param roleId
	 * @return
	 * @author SixSir
	 */
	 public void deleteByRole(Long roleId);

}
