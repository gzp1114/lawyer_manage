package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class LawyerCourt extends DomainBase implements Serializable{

	public LawyerCourt() {
		
	}

	/**
	*  主键
	*/
	private Long id;

	/**
	*  父节点
	*/
	private Long pid;

	/**
	*  法院名称
	*/
	private String courtName;

	/**
	*  法院编码
	*/
	private String number;
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setPid(Long pid){
		this.pid = pid;
	}
	
	public Long getPid(){
		return pid;
	}
	
	public void setCourtName(String courtName){
		this.courtName = courtName;
	}
	
	public String getCourtName(){
		return courtName;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getNumber(){
		return number;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
