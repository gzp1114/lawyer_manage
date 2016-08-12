package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@MyBatisDomain
public class SysContectInterview extends DomainBase implements Serializable{

	public SysContectInterview() {
		
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
	*  跟进人id
	*/
	private Long userId;

	/**
	*  快递发送时间
	*/
	private String sendTime;

	/**
	*  发送结果
	*/
	private String sendResult;

	/**
	*  约谈人
	*/
	private String interviewName;

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
	
	public void setSendTime(String sendTime){
		this.sendTime = sendTime;
	}
	
	public String getSendTime(){
		return sendTime;
	}
	
	public void setSendResult(String sendResult){
		this.sendResult = sendResult;
	}
	
	public String getSendResult(){
		return sendResult;
	}
	
	public void setInterviewName(String interviewName){
		this.interviewName = interviewName;
	}
	
	public String getInterviewName(){
		return interviewName;
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
