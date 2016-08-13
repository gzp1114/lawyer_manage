package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class SysLawyerSource extends DomainBase implements Serializable{

	public SysLawyerSource() {
		
	}

	/**
	*  主键id
	*/
	private Long id;

	/**
	*  债务人id
	*/
	private Long debtorId;

	/**
	*  债权人id
	*/
	private Long claimId;

	/**
	*  联系人id
	*/
	private Long userId;

	/**
	*  联系时间
	*/
	private Date contectTime;

	/**
	*  创建时间
	*/
	private Date createtime;

	/**
	*  备注
	*/
	private String note;

	/**
	*  操作人id
	*/
	private Long operatorId;
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setDebtorId(Long debtorId){
		this.debtorId = debtorId;
	}
	
	public Long getDebtorId(){
		return debtorId;
	}
	
	public void setClaimId(Long claimId){
		this.claimId = claimId;
	}
	
	public Long getClaimId(){
		return claimId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setContectTime(Date contectTime){
		this.contectTime = contectTime;
	}
	
	public Date getContectTime(){
		return contectTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}
	
	public Date getCreatetime(){
		return createtime;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
