package com.lawyer.system.usercenter.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class SysUserRole extends DomainBase implements Serializable {
	public SysUserRole() {

	}

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 租户编号,租户表外键
	 */
	private Long tenantId;

	/**
	 * 用户状态表外键ID
	 */
	private Long userSessionId;

	/**
	 * 角色信息表外键ID
	 */
	private Long roleId;

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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setUserSessionId(Long userSessionId) {
		this.userSessionId = userSessionId;
	}

	public Long getUserSessionId() {
		return userSessionId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleId() {
		return roleId;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
