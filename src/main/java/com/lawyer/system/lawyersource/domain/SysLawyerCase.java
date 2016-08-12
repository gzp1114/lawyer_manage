package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@MyBatisDomain
public class SysLawyerCase extends DomainBase implements Serializable{

	public SysLawyerCase() {
		
	}

	/**
	*  主键
	*/
	private Long id;

	/**
	*  案源id
	*/
	private Long sourceId;

	/**
	*  签约信息id
	*/
	private Long signId;

	/**
	*  创建时间
	*/
	private Date createtime;

	/**
	*  备注
	*/
	private String note;
	
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
	
	public void setSignId(Long signId){
		this.signId = signId;
	}
	
	public Long getSignId(){
		return signId;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
