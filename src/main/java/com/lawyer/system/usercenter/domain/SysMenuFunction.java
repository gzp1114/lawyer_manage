package com.lawyer.system.usercenter.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class SysMenuFunction extends DomainBase implements Serializable{

	public SysMenuFunction() {
		
	}

	/**
	*  主键
	*/
	private Long id;

	/**
	*  上一级菜单主键
	*/
	private Long parentId;

	/**
	*  租户编号
	*/
	private Long tenantId;

	/**
	*  菜单名
	*/
	private String menuName;

	/**
	*  菜单类型，值：1为模块，2为菜单，3为功能
	*/
	private Integer menuType;

	/**
	*  是否有效，值：0为无效，1为有效
	*/
	private Integer isValid;

	/**
	*  菜单URL
	*/
	private String menuUrl;

	/**
	*  菜单功能说明
	*/
	private String menuInfo;

	/**
	*  备注
	*/
	private String note;

	/**
	*  创建时间
	*/
	private Date createTime;

	/**
	*  时间截
	*/
	private Date timestamp;
	
	private boolean userHaved = false; 
	
	private List<SysMenuFunction> children = new ArrayList<SysMenuFunction>();
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public Long getParentId(){
		return parentId;
	}
	
	public void setTenantId(Long tenantId){
		this.tenantId = tenantId;
	}
	
	public Long getTenantId(){
		return tenantId;
	}
	
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	
	public String getMenuName(){
		return menuName;
	}
	
	public void setMenuType(Integer menuType){
		this.menuType = menuType;
	}
	
	public Integer getMenuType(){
		return menuType;
	}
	
	public void setIsValid(Integer isValid){
		this.isValid = isValid;
	}
	
	public Integer getIsValid(){
		return isValid;
	}
	
	public void setMenuUrl(String menuUrl){
		this.menuUrl = menuUrl;
	}
	
	public String getMenuUrl(){
		return menuUrl;
	}
	
	public void setMenuInfo(String menuInfo){
		this.menuInfo = menuInfo;
	}
	
	public String getMenuInfo(){
		return menuInfo;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	public String getNote(){
		return note;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setTimestamp(Date timestamp){
		this.timestamp = timestamp;
	}
	
	public Date getTimestamp(){
		return timestamp;
	}
	
	public List<SysMenuFunction> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenuFunction> children) {
		this.children = children;
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
