package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class SysDebtorCompany extends DomainBase implements Serializable{

	public SysDebtorCompany() {
		
	}

	/**
	*  主键id
	*/
	private Long id;

	/**
	*  公司名称
	*/
	private String name;

	/**
	*  组织结构代码
	*/
	private String organizationCode;

	/**
	*  公司注册号
	*/
	private String registerNumber;

	/**
	*  公司类型
	*/
	private String type;

	/**
	*  公司法人
	*/
	private String corporateName;

	/**
	*  法人身份证号
	*/
	private String corporateCardnum;

	/**
	*  公司成立日期
	*/
	private String establishDate;

	/**
	*  注册资金
	*/
	private String registerCapital;

	/**
	*  营业开始日期
	*/
	private String businessStartDate;

	/**
	*  营业结束日期
	*/
	private String businessEndDate;

	/**
	*  登记机关
	*/
	private String registrationAuthority;

	/**
	*  公司地址
	*/
	private String address;

	/**
	*  企业经营状态
	*/
	private String operateStatus;

	/**
	*  创建时间
	*/
	private Date createTime;

	/**
	*  备注
	*/
	private String note;

	/**
	*  操作人id
	*/
	private Long operatorId;
	
	/**
	*  被执行信息
	*/
	private List<SysDebtor> debtors;
	
	/**
	*  公告信息
	*/
	private List<SysAnnouncement> announcements;
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setOrganizationCode(String organizationCode){
		this.organizationCode = organizationCode;
	}
	
	public String getOrganizationCode(){
		return organizationCode;
	}
	
	public void setRegisterNumber(String registerNumber){
		this.registerNumber = registerNumber;
	}
	
	public String getRegisterNumber(){
		return registerNumber;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public void setCorporateName(String corporateName){
		this.corporateName = corporateName;
	}
	
	public String getCorporateName(){
		return corporateName;
	}
	
	public void setCorporateCardnum(String corporateCardnum){
		this.corporateCardnum = corporateCardnum;
	}
	
	public String getCorporateCardnum(){
		return corporateCardnum;
	}
	
	public void setEstablishDate(String establishDate){
		this.establishDate = establishDate;
	}
	
	public String getEstablishDate(){
		return establishDate;
	}
	
	public void setRegisterCapital(String registerCapital){
		this.registerCapital = registerCapital;
	}
	
	public String getRegisterCapital(){
		return registerCapital;
	}
	
	public void setBusinessStartDate(String businessStartDate){
		this.businessStartDate = businessStartDate;
	}
	
	public String getBusinessStartDate(){
		return businessStartDate;
	}
	
	public void setBusinessEndDate(String businessEndDate){
		this.businessEndDate = businessEndDate;
	}
	
	public String getBusinessEndDate(){
		return businessEndDate;
	}
	
	public void setRegistrationAuthority(String registrationAuthority){
		this.registrationAuthority = registrationAuthority;
	}
	
	public String getRegistrationAuthority(){
		return registrationAuthority;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setOperateStatus(String operateStatus){
		this.operateStatus = operateStatus;
	}
	
	public String getOperateStatus(){
		return operateStatus;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	public String getNote(){
		return note;
	}
	
	public void setOperatorId(Long operatorId){
		this.operatorId = operatorId;
	}
	
	public Long getOperatorId(){
		return operatorId;
	}
	
	public List<SysDebtor> getDebtors() {
		return debtors;
	}

	public void setDebtors(List<SysDebtor> debtors) {
		this.debtors = debtors;
	}

	public List<SysAnnouncement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<SysAnnouncement> announcements) {
		this.announcements = announcements;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
