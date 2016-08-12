package com.lawyer.system.usercenter.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class SysUserSession extends DomainBase implements Serializable {
	public SysUserSession() {

	}

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户名称
	 */
	private String username;

	/**
	 * 用户手机号
	 */
	private String phone;

	/**
	 * 登录账户
	 */
	private String loginAccount;

	/**
	 * 登录密码
	 */
	private String loginPassword;

	/**
	 * 状态，值：1为正常、2为停用、3为删除
	 */
	private Integer status;
	
	/**
	 * 会话ID
	 */
	private String sessionId;

	/**
	 * 最后一次登陆时间
	 */
	private Date lastLoginTime;

	/**
	 * 登陆IP
	 */
	private String loginIp;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 时间截
	 */
	private Date timestamp;
	
	/**
	 * 状态，值：1为看自己的权限，2为看全部权限
	 */
	private Integer dataAuth;
	
	private List<SysRole> roleList;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setDataAuth(Integer dataAuth) {
		this.dataAuth = dataAuth;
	}

	public Integer getDataAuth() {
		return dataAuth;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
