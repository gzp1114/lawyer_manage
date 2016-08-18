package com.lawyer.system.usercenter.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.usercenter.domain.SysUserSession;

@MyBatisRepository
public interface SysUserSessionMapper {

	/**
	 * save sys_user_session
	 * 
	 * @param SysUserSession
	 * @return
	 */
	public void save(SysUserSession sysUserSession);

	/**
	 * update sys_user_session
	 * 
	 * @param SysUserSession
	 * @return
	 */
	public void update(SysUserSession sysUserSession);
	
	/**
	* 按条件分页查询记录
	* @param searchParams 条件
	* @return
	*/
	List<SysUserSession> searchByPage(Map<String,Object> searchParams);

	/**
	 * 获取全部用户信息
	 * @return
	 */
	public List<SysUserSession> findAll();
	
	/**
	 * query sys_user_session by primary key
	 * 
	 * @param
	 * @return SysUserSession
	 */
	public SysUserSession findById(Long id);
	
	/**
	 * query sys_user_session by login_account
	 * 
	 * @param
	 * @return SysUserSession
	 */
	public SysUserSession findByLoginAccount(String loginAccount);
	/**
	 * delete sys_user_session by primary key
	 * 
	 * @param SysUserSession
	 * @return
	 */
	public void delete(Long id);
	
	/**
	 * 查询用户的角色列表
	 * @param loginAccount
	 * @return
	 */
	public SysUserSession selectUserRoles(String loginAccount);
	
	/**
	 * 查询用户未拥有的角色列表
	 * @param loginAccount
	 * @return
	 */
	public SysUserSession selectUserNoRoles(String loginAccount);


}
