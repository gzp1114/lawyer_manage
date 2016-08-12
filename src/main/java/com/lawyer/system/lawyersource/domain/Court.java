package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class Court extends DomainBase implements Serializable{

	public Court() {
		
	}

	/**
	*  主键
	*/
	private Long id;

	/**
	*  案件标号
	*/
	private String caseId;
	
	/**
	*  被执行人姓名/名称
	*/
	private String pname;
	
	/**
	*  身份证号码/组织机构代码
	*/
	private String partyCardNum;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPartyCardNum() {
		return partyCardNum;
	}

	public void setPartyCardNum(String partyCardNum) {
		this.partyCardNum = partyCardNum;
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
