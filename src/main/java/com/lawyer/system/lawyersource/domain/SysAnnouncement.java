package com.lawyer.system.lawyersource.domain;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.lawyer.cores.framework.domain.DomainBase;
import com.lawyer.cores.framework.mybatis.MyBatisDomain;

@MyBatisDomain
public class SysAnnouncement extends DomainBase implements Serializable{

	public SysAnnouncement() {
		
	}

	/**
	*  主键
	*/
	private Long id;

	/**
	*  债务人id
	*/
	private Long debtorCompanyId;

	/**
	*  公告法院
	*/
	private String announcementCourt;

	/**
	*  公告日期
	*/
	private String announcementDate;

	/**
	*  刊登版面
	*/
	private String publishedPage;

	/**
	*  备注
	*/
	private String note;

	/**
	*  详情链接地址
	*/
	private String url;

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
	
	public void setAnnouncementCourt(String announcementCourt){
		this.announcementCourt = announcementCourt;
	}
	
	public String getAnnouncementCourt(){
		return announcementCourt;
	}
	
	public void setAnnouncementDate(String announcementDate){
		this.announcementDate = announcementDate;
	}
	
	public String getAnnouncementDate(){
		return announcementDate;
	}
	
	public void setPublishedPage(String publishedPage){
		this.publishedPage = publishedPage;
	}
	
	public String getPublishedPage(){
		return publishedPage;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	public String getNote(){
		return note;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
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
