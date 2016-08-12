package com.lawyer.system.usercenter.dto;

import java.io.Serializable;

import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class SysUserInfoDto implements Serializable {

	/**
	 * 用户ID
	 */
	private Long id;

	/**
	 * 登录账户
	 */
	private String loginAccount;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 状态，值：1为正常、2为停用、3为删除
	 */
	private Integer status;
	
	/**
	 * 账号类型：0为admin、1为平台管理员、2为租户管理员、3为普通用户
	 */
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
