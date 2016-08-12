package com.lawyer.system.usercenter.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class SysRole extends DomainBase implements Serializable {
	public SysRole() {

	}

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 父角色主键
	 */
	private Long parentId;

	/**
	 * 租户编号,租户表外键
	 */
	private Long tenantId;

	/**
	 * 角色名称
	 */
	private String roleName;

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
	
	private boolean userHaved = false;
	
	private List<SysMenuFunction> menuFunctions;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
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

	public List<SysMenuFunction> getMenuFunctions() {
		return menuFunctions;
	}

	public void setMenuFunctions(List<SysMenuFunction> menuFunctions) {
		this.menuFunctions = menuFunctions;
	}

	public boolean isUserHaved() {
		return userHaved;
	}

	public void setUserHaved(boolean userHaved) {
		this.userHaved = userHaved;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
