package com.lawyer.system.usercenter.domain;

import java.io.Serializable;


public class ShiroUser implements Serializable{

	private static final long serialVersionUID = 8891396092953288410L;
	private String loginName;
	private SysUserSession user;
	
	public ShiroUser() {
		
	}
	
	/**  
	 * 构造函数
	 * @param id
	 * @param loginName
	 * @param email
	 * @param createTime
	 * @param status  
	 */ 
	public ShiroUser(String loginName, SysUserSession user) {
		this.loginName = loginName;
		this.user = user;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setUser(SysUserSession user) {
		this.user = user;
	}

	/**  
	 * 返回 loginName 的值   
	 * @return loginName  
	 */
	public String getLoginName() {
		return loginName;
	}

	/**  
	 * 返回 user 的值   
	 * @return user  
	 */
	public SysUserSession getUser() {
		return user;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}
}
