package com.lawyer.system.usercenter.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.usercenter.domain.SysRoleMenu;

@MyBatisRepository
public interface SysRoleMenuMapper {

  	/**
	 * save sys_role_menu
	 * @param SysRoleMenu
	 * @return 
	 */
	 public void save(SysRoleMenu sysRoleMenu);
	 
	 /**
	 * 批量插入sys_role_menu
	 * @param map
	 */
	 public void saveMore(Map<String, Object> map);
	
	/**
	 * update sys_role_menu
	 * @param SysRoleMenu
	 * @return 
	 */
	 public void update(SysRoleMenu sysRoleMenu);
  	/**
	 * query sys_role_menu by primary key 
	 * @param Long id
	 * @return SysRoleMenu
	 */
	 public SysRoleMenu findById(Long id);
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<SysRoleMenu> searchByPage(Map<String,Object> searchParams);
	 
	 /**
	 * delete sys_role_menu by primary key 
	 * @param SysRoleMenu
	 * @return 
	 */
	 public void deleteById(Long id);

	 /**
	 * delete sys_role_menu by roleId
	 * @param SysRoleMenu
	 * @return 
	 */
	 public void deleteByRole(Long roleId);
	 
}
