package com.lawyer.system.lawyersource.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawyer.cores.framework.mybatis.PageParameter;
import com.lawyer.cores.result.Results;
import com.lawyer.system.lawyersource.dao.mybatis.SysAnnouncementMapper;
import com.lawyer.system.lawyersource.dao.mybatis.SysClaimCompanyMapper;
import com.lawyer.system.lawyersource.dao.mybatis.SysContectEmailMapper;
import com.lawyer.system.lawyersource.dao.mybatis.SysDebtorCompanyMapper;
import com.lawyer.system.lawyersource.dao.mybatis.SysDebtorMapper;
import com.lawyer.system.lawyersource.dao.mybatis.SysLawyerSourceMapper;
import com.lawyer.system.lawyersource.domain.SysAnnouncement;
import com.lawyer.system.lawyersource.domain.SysClaimCompany;
import com.lawyer.system.lawyersource.domain.SysContectEmail;
import com.lawyer.system.lawyersource.domain.SysDebtor;
import com.lawyer.system.lawyersource.domain.SysDebtorCompany;
import com.lawyer.system.lawyersource.domain.SysLawyerSource;
import com.lawyer.system.usercenter.service.UserCenterContents;

@Service("sysLawyerSourceService")
public class SysLawyerSourceService {
	
	private static Logger logger = LoggerFactory.getLogger(SysLawyerSourceService.class);
	
	@Autowired
	private SysLawyerSourceMapper sysLawyerSourceDao;
	@Autowired
	private SysDebtorCompanyMapper sysDebtorCompanyDao;
	@Autowired
	private SysDebtorMapper sysDebtorDao;
	@Autowired
	private SysAnnouncementMapper sysAnnouncementDao;
	@Autowired
	private SysClaimCompanyMapper sysClaimCompanyDao;
	@Autowired
	private SysContectEmailMapper sysContectEmailDao;
	
	/**
	 * 增加案源信息
	 * @param sysLawyerSource
	 * @return Results
	 */
	public Results add(SysLawyerSource sysLawyerSource){
		
		sysLawyerSourceDao.save(sysLawyerSource);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		return results; 
	}

	
	/**
	 * 删除案源信息
	 * @param Long id
	 * @return Results
	 */
	public Results deleteById(Long id){

		sysLawyerSourceDao.deleteById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改案源信息
	 * @param sysLawyerSource
	 * @return Results
	 */
	public Results modify(SysLawyerSource sysLawyerSource){

		sysLawyerSourceDao.update(sysLawyerSource);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询案源信息
	 * @param sysLawyerSource
	 * @return Results
	 */
	public Results findById(Long id){

		SysLawyerSource sysLawyerSource = sysLawyerSourceDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysLawyerSource);
		return results; 
	}

	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return Results
	 */
	public Results searchByPage(Map<String,Object> searchParams,Integer pageNumber, Integer pageSize) {
		
		PageParameter page = new PageParameter(); 
		page.setCurrentPage(pageNumber); 
		page.setPageSize(pageSize); 
		searchParams.put("page", page); 

		List<SysLawyerSource> list = sysLawyerSourceDao.searchByPage(searchParams);
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("total", page.getTotalPage());
		rmap.put("page", page.getCurrentPage());
		rmap.put("records", page.getTotalCount());
		rmap.put("pageSize", page.getPageSize());
		rmap.put("rows", list);
		
		String debtorName = searchParams.get("debtorName")==null?"":String.valueOf(searchParams.get("debtorName"));
		String claimName = searchParams.get("claimName")==null?"":String.valueOf(searchParams.get("claimName"));
		rmap.put("debtorName", debtorName); 
		rmap.put("claimName", claimName); 
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),rmap);
		
		return results; 
	}
	
	/**
	 * 查询债务详细信息
	 * @param sysDebtorCompany
	 * @return Results
	 */
	public Results showInfo(Long id){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		SysLawyerSource lawyerSource = sysLawyerSourceDao.findById(id);
		if(lawyerSource == null){
			return results;
		}
		
		SysDebtorCompany sysDebtorCompany = sysDebtorCompanyDao.findById(lawyerSource.getDebtorId());
		if(sysDebtorCompany == null){
			return results;
		}
		List<SysDebtor> debtors = sysDebtorDao.findByCompanyid(sysDebtorCompany.getId());
		sysDebtorCompany.setDebtors(debtors);
		List<SysAnnouncement> announcements = sysAnnouncementDao.findByCompanyid(sysDebtorCompany.getId());
		sysDebtorCompany.setAnnouncements(announcements);
		
		SysClaimCompany sysClaimCompany = sysClaimCompanyDao.findById(lawyerSource.getClaimId());
		List<SysContectEmail> contectEmails= sysContectEmailDao.findBySourceId(lawyerSource.getId());
		
		lawyerSource.setDebtorCompany(sysDebtorCompany);
		lawyerSource.setClaimCompany(sysClaimCompany);
		lawyerSource.setContectEmails(contectEmails);
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("lawyerSource", lawyerSource);
		rmap.put("lawyerCount", debtors.size()+announcements.size());
		rmap.put("contectCount", contectEmails.size());
		
		results.setData(rmap);
		return results; 
	}

}
