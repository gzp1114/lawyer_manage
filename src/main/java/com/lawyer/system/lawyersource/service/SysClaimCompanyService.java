package com.lawyer.system.lawyersource.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawyer.cores.framework.mybatis.PageParameter;
import com.lawyer.cores.result.Results;
import com.lawyer.system.lawyersource.dao.mybatis.SysClaimCompanyMapper;
import com.lawyer.system.lawyersource.dao.mybatis.SysLawyerSourceMapper;
import com.lawyer.system.lawyersource.domain.SysClaimCompany;
import com.lawyer.system.lawyersource.domain.SysLawyerSource;
import com.lawyer.system.usercenter.domain.SysUserSession;
import com.lawyer.system.usercenter.service.UserCenterContents;

@Service("sysClaimCompanyService")
public class SysClaimCompanyService {
	
	private static Logger logger = LoggerFactory.getLogger(SysClaimCompanyService.class);
	
	@Autowired
	private SysClaimCompanyMapper sysClaimCompanyDao;
	@Autowired
	private SysLawyerSourceMapper sysLawyerSourceDao;

	
	/**
	 * 增加债权人信息
	 * @param sysClaimCompany
	 * @return Results
	 */
	public Results add(SysClaimCompany sysClaimCompany,String debtorCompanyId){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		SysUserSession user = (SysUserSession) SecurityUtils.getSubject().getSession().getAttribute("sysUser");
		
		if(debtorCompanyId == null){
			results.setStatus(UserCenterContents.API_RETURN_STATUS.PARA_ERROR.value());
			results.setError(UserCenterContents.API_RETURN_STATUS.PARA_ERROR.desc());
			return results;
		}
		
		SysClaimCompany local = sysClaimCompanyDao.findByName(sysClaimCompany.getName());
		if(local == null){
			sysClaimCompany.setOperatorId(user.getId());
			sysClaimCompany.setCreateTime(new Date());
			sysClaimCompanyDao.save(sysClaimCompany);
			logger.info("保存债权信息成功："+sysClaimCompany.getName());
		}else{
			sysClaimCompany = local;
		}
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("debtorId", debtorCompanyId);
		rmap.put("claimId", sysClaimCompany.getId());
		SysLawyerSource lawyerSource = sysLawyerSourceDao.findUnique(rmap);
		if(lawyerSource != null){
			
			results.setStatus("-1");
			results.setError("已存在此案源信息");
			return results;
			
		}
		
		lawyerSource = new SysLawyerSource();
		lawyerSource.setClaimId(sysClaimCompany.getId());
		lawyerSource.setDebtorId(Long.valueOf(debtorCompanyId));
		lawyerSource.setCreatetime(new Date());
		lawyerSource.setOperatorId(user.getId());
		sysLawyerSourceDao.save(lawyerSource);
		logger.info("保存案源信息成功");
		
		return results; 
	}

	
	/**
	 * 删除债权人信息
	 * @param Long id
	 * @return Results
	 */
	public Results deleteById(Long id){

		sysClaimCompanyDao.deleteById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改债权人信息
	 * @param sysClaimCompany
	 * @return Results
	 */
	public Results modify(SysClaimCompany sysClaimCompany){

		sysClaimCompanyDao.update(sysClaimCompany);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询债权人信息
	 * @param sysClaimCompany
	 * @return Results
	 */
	public Results findById(Long id){

		SysClaimCompany sysClaimCompany = sysClaimCompanyDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysClaimCompany);
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

		List<SysClaimCompany> list = sysClaimCompanyDao.searchByPage(searchParams);
		
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

}
