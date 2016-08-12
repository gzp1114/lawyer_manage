package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@MyBatisDomain
public class SysContectSign extends DomainBase implements Serializable{

	public SysContectSign() {
		
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
	*  签约号
	*/
	private String signNumber;

	/**
	*  签约人id
	*/
	private Long userId;

	/**
	*  签约时间
	*/
	private String signTime;

	/**
	*  签约比例
	*/
	private String signResult;

	/**
	*  签约联系人
	*/
	private String contectName;

	/**
	*  签约联系电话
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
	
	public void setSignNumber(String signNumber){
		this.signNumber = signNumber;
	}
	
	public String getSignNumber(){
		return signNumber;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setSignTime(String signTime){
		this.signTime = signTime;
	}
	
	public String getSignTime(){
		return signTime;
	}
	
	public void setSignResult(String signResult){
		this.signResult = signResult;
	}
	
	public String getSignResult(){
		return signResult;
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
