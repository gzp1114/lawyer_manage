package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@MyBatisDomain
public class SysContectPhone extends DomainBase implements Serializable{

	public SysContectPhone() {
		
	}

	/**
	*  主键
	*/
	private Long id;

	/**
	*  案源信息id
	*/
	private Long sourceId;

	/**
	*  打电话人id
	*/
	private Long userId;

	/**
	*  打电话号码
	*/
	private String phoneNumber;

	/**
	*  打电话时间
	*/
	private String phoneTime;

	/**
	*  打电话结果
	*/
	private String phoneResult;

	/**
	*  联系人名称
	*/
	private String contectName;

	/**
	*  联系人电话
	*/
	private String contectPhone;

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
	
	public void setSourceId(Long sourceId){
		this.sourceId = sourceId;
	}
	
	public Long getSourceId(){
		return sourceId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setPhoneTime(String phoneTime){
		this.phoneTime = phoneTime;
	}
	
	public String getPhoneTime(){
		return phoneTime;
	}
	
	public void setPhoneResult(String phoneResult){
		this.phoneResult = phoneResult;
	}
	
	public String getPhoneResult(){
		return phoneResult;
	}
	
	public void setContectName(String contectName){
		this.contectName = contectName;
	}
	
	public String getContectName(){
		return contectName;
	}
	
	public void setContectPhone(String contectPhone){
		this.contectPhone = contectPhone;
	}
	
	public String getContectPhone(){
		return contectPhone;
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
