package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@MyBatisDomain
public class SysDebtor extends DomainBase implements Serializable{

	public SysDebtor() {
		
	}

	/**
	*  主键
	*/
	private Long id;

	/**
	*  债务公司id
	*/
	private Long debtorCompanyId;

	/**
	*  案件标号
	*/
	private String caseId;

	/**
	*  执行法院
	*/
	private String execCourtname;

	/**
	*  法院编号
	*/
	private String courtcode;

	/**
	*  立案时间
	*/
	private String caseCreatetime;

	/**
	*  案号
	*/
	private String casecode;

	/**
	*  执行标的
	*/
	private double  executeMoney;

	/**
	*  案件状态
	*/
	private String caseState;

	/**
	*  备注
	*/
	private String note;

	/**
	*  创建时间
	*/
	private Date createtime;

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
	
	public void setDebtorCompanyId(Long debtorCompanyId){
		this.debtorCompanyId = debtorCompanyId;
	}
	
	public Long getDebtorCompanyId(){
		return debtorCompanyId;
	}
	
	public void setCaseId(String caseId){
		this.caseId = caseId;
	}
	
	public String getCaseId(){
		return caseId;
	}
	
	public void setExecCourtname(String execCourtname){
		this.execCourtname = execCourtname;
	}
	
	public String getExecCourtname(){
		return execCourtname;
	}
	
	public void setCourtcode(String courtcode){
		this.courtcode = courtcode;
	}
	
	public String getCourtcode(){
		return courtcode;
	}
	
	public void setCaseCreatetime(String caseCreatetime){
		this.caseCreatetime = caseCreatetime;
	}
	
	public String getCaseCreatetime(){
		return caseCreatetime;
	}
	
	public void setCasecode(String casecode){
		this.casecode = casecode;
	}
	
	public String getCasecode(){
		return casecode;
	}
	
	public void setExecuteMoney(double executeMoney){
		this.executeMoney = executeMoney;
	}
	
	public double  getExecuteMoney(){
		return executeMoney;
	}
	
	public void setCaseState(String caseState){
		this.caseState = caseState;
	}
	
	public String getCaseState(){
		return caseState;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	public String getNote(){
		return note;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}
	
	public Date getCreatetime(){
		return createtime;
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
