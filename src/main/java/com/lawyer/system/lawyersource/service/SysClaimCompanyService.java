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
import com.lawyer.system.lawyersource.dao.mybatis.SysClaimCompanyMapper;
import com.lawyer.system.lawyersource.domain.SysClaimCompany;
import com.lawyer.system.usercenter.service.UserCenterContents;

@Service("sysClaimCompanyService")
public class SysClaimCompanyService {
	
	private static Logger logger = LoggerFactory.getLogger(SysClaimCompanyService.class);
	
	@Autowired
	private SysClaimCompanyMapper sysClaimCompanyDao;

	
	/**
	 * 增加债权人信息
	 * @param sysClaimCompany
	 * @return Results
	 */
	public Results add(SysClaimCompany sysClaimCompany){
		
		sysClaimCompanyDao.save(sysClaimCompany);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
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
		
		Map rmap = new HashMap();
		rmap.put("total", page.getTotalPage());
		rmap.put("page", page.getCurrentPage());
		rmap.put("records", page.getTotalCount());
		
		rmap.put("rows", list);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),rmap);
		
		return results; 
	}

}
