package com.lawyer.system.lawyersource.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawyer.cores.framework.mybatis.PageParameter;
import com.lawyer.cores.result.Results;
import com.lawyer.cores.tools.other.PropertiesUtil;
import com.lawyer.cores.utils.ComUtil;
import com.lawyer.system.lawyersource.dao.mybatis.CourtMapper;
import com.lawyer.system.lawyersource.dao.mybatis.SysAnnouncementMapper;
import com.lawyer.system.lawyersource.dao.mybatis.SysDebtorCompanyMapper;
import com.lawyer.system.lawyersource.dao.mybatis.SysDebtorMapper;
import com.lawyer.system.lawyersource.domain.Court;
import com.lawyer.system.lawyersource.domain.SysAnnouncement;
import com.lawyer.system.lawyersource.domain.SysDebtor;
import com.lawyer.system.lawyersource.domain.SysDebtorCompany;
import com.lawyer.system.usercenter.domain.SysUserSession;
import com.lawyer.system.usercenter.service.UserCenterContents;

@Service("sysDebtorCompanyService")
public class SysDebtorCompanyService<K> {
	
	private static Logger logger = LoggerFactory.getLogger(SysDebtorCompanyService.class);
	
	@Autowired
	private SysDebtorCompanyMapper sysDebtorCompanyDao;
	@Autowired
	private SysDebtorMapper sysDebtorDao;
	@Autowired
	private SysAnnouncementMapper sysAnnouncementDao;
	@Autowired
	private CourtMapper courtDao;

	
	/**
	 * 增加债务人信息
	 * @param sysDebtorCompany
	 * @return Results
	 */
	public Results add(SysDebtorCompany sysDebtorCompany){
		
		sysDebtorCompanyDao.save(sysDebtorCompany);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		return results; 
	}

	
	/**
	 * 删除债务人信息
	 * @param Long id
	 * @return Results
	 */
	public Results deleteById(Long id){

		sysDebtorCompanyDao.deleteById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改债务人信息
	 * @param sysDebtorCompany
	 * @return Results
	 */
	public Results modify(SysDebtorCompany sysDebtorCompany){

		sysDebtorCompanyDao.update(sysDebtorCompany);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询债务人信息
	 * @param sysDebtorCompany
	 * @return Results
	 */
	public Results findById(Long id){

		SysDebtorCompany sysDebtorCompany = sysDebtorCompanyDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysDebtorCompany);
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
		
		SysDebtorCompany sysDebtorCompany = sysDebtorCompanyDao.findById(id);
		if(sysDebtorCompany == null){
			return results;
		}
		List<SysDebtor> debtors = sysDebtorDao.findByCompanyid(id);
		sysDebtorCompany.setDebtors(debtors);
		List<SysAnnouncement> announcements = sysAnnouncementDao.findByCompanyid(id);
		sysDebtorCompany.setAnnouncements(announcements);
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("debtorCompany", sysDebtorCompany);
		rmap.put("lawyerCount", debtors.size()+announcements.size());
		rmap.put("debtorCount", debtors.size());
		rmap.put("announcementCount", announcements.size());
		
		results.setData(rmap);
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

		List<SysDebtorCompany> list = sysDebtorCompanyDao.searchByPage(searchParams);
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("total", page.getTotalPage());
		rmap.put("page", page.getCurrentPage());
		rmap.put("records", page.getTotalCount());
		rmap.put("pageSize", page.getPageSize());
		rmap.put("rows", list);
		
		String name = searchParams.get("name")==null?"":String.valueOf(searchParams.get("name"));
		rmap.put("name", name);  
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),rmap);
		
		return results; 
	}
	
	/**
	 * 查询债务人信息
	 * @param sysDebtorCompany
	 * @return Results
	 */
	public Results importDebtor(Integer type,HttpServletRequest request){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		String limitCount = PropertiesUtil.getPropByKey("limit_count");
		SysUserSession user = (SysUserSession) request.getSession().getAttribute("sysUser");
		//type   1被执行信息      2公告信息
		switch (type) {
		case 1:
			results = importDebtors(limitCount, user);
			break;
		case 2:
			
			break;
		default:
			break;
		}

		return results; 
	}
	
	public Results importDebtors(String limitCount,SysUserSession user){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		int count = 0;
		
		List<Court> courts = courtDao.findByCount(Long.valueOf(limitCount));
		if(courts.size() <= 0){
			results.setData("被执行信息已经处理完毕，没有要处理信息！！！");
			return results;
		}
		for (int i = 0; i < courts.size(); i++) { 
			Court court = courts.get(i);
			String companyName = ComUtil.getCompanyName(court.getPname());
			SysDebtorCompany debtorCompany = sysDebtorCompanyDao.findByName(companyName);
			if(debtorCompany == null){
				debtorCompany = new SysDebtorCompany();
				debtorCompany.setName(companyName);
				debtorCompany.setOrganizationCode(court.getPartyCardNum());
				debtorCompany.setCreateTime(new Date());
				debtorCompany.setOperatorId(user.getId());
				
				sysDebtorCompanyDao.save(debtorCompany);
				logger.info("保存债务主体信息："+court.getPname());
			}
			
			Map<String,Object> searchParams = new HashMap<>();
			searchParams.put("debtorCompanyId", debtorCompany.getId());
			searchParams.put("casecode", court.getCasecode());
			List<SysDebtor> list= sysDebtorDao.findByCidCasecode(searchParams);
			if(list.size() > 0){
				continue;
			}
			
			SysDebtor debtor = new SysDebtor();
			debtor.setDebtorCompanyId(debtorCompany.getId());
			debtor.setCaseId(court.getCaseId());
			debtor.setExecCourtname(court.getExecCourtname());
			debtor.setCourtcode(court.getCourtcode());
			debtor.setCaseCreatetime(court.getCaseCreatetime());
			debtor.setCasecode(court.getCasecode());
			debtor.setExecuteMoney(court.getExecuteMoney());
			debtor.setCaseState(court.getCaseState());
			debtor.setCreatetime(new Date());
			debtor.setOperatorId(user.getId());
			
			try {
				sysDebtorDao.save(debtor);
				logger.info("保存被执行信息成功："+debtor.getDebtorCompanyId()+"=="+debtor.getCasecode());
			} catch (Exception e) {
				logger.error("保存被执行信息失败："+e.getMessage());
				continue;
			}
			
			count++;
		}
		courtDao.deleteByCount(Long.valueOf(limitCount));
		
		results.setData("批处理数据完成,查询"+limitCount+"条，实际处理"+count+"条数据");
		return results;
	}

}
