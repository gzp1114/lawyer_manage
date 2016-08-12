package com.lawyer.system.usercenter.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.usercenter.domain.SysMenuFunction;

@MyBatisRepository
public interface SysMenuFunctionMapper {

  	/**
	 * save sys_menu_function
	 * @param SysMenuFunction
	 * @return 
	 */
	 public void save(SysMenuFunction sysMenuFunction);
	
	/**
	 * update sys_menu_function
	 * @param SysMenuFunction
	 * @return 
	 */
	 public void update(SysMenuFunction sysMenuFunction);
  	/**
	 * query sys_menu_function by primary key 
	 * @param  
	 * @return SysMenuFunction
	 */
	 public SysMenuFunction findById(Long id );
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<SysMenuFunction> searchByPage(Map<String,Object> searchParams);	 
	/**
	 * 检查URL是否存在,返回相同URL的个数
	 * @param searchParams 条件
	 * @return
	 */
	 public Long checkMenuUrl(Map<String,Object> searchParams);
	 
	 /**
	 * delete sys_menu_function by primary key 
	 * @param SysMenuFunction
	 * @return 
	 */
	 public void deleteById(Long id );
	 
	 /**
	  * 获取菜单树
	  * @return
	  */
	 public List<SysMenuFunction> getMenuTree(Long id);

	 /**
	  * 获取菜单权限树
	  * @return
	  */
	 public List<SysMenuFunction> getAllTree();

	 /**
	  * 根据parentID获取所有子菜单ID
	  * @param id 父级菜单ID
	  * @return
	  */
	 public List<SysMenuFunction> getChildByParentId(Long id);
	 
	 /**
	  * 根据menuUrl获取菜单信息
	  * @param menuUrl
	  * @return
	  */
	 public SysMenuFunction findByUrl(String menuUrl);
	 
}
