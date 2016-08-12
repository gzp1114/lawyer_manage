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
import com.lawyer.system.lawyersource.dao.mybatis.SysDebtorMapper;
import com.lawyer.system.lawyersource.domain.SysDebtor;
import com.lawyer.system.usercenter.service.UserCenterContents;

@Service("sysDebtorService")
public class SysDebtorService {
	
	private static Logger logger = LoggerFactory.getLogger(SysDebtorService.class);
	
	@Autowired
	private SysDebtorMapper sysDebtorDao;

	
	/**
	 * 增加被执行信息
	 * @param sysDebtor
	 * @return Results
	 */
	public Results add(SysDebtor sysDebtor){
		
		sysDebtorDao.save(sysDebtor);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		return results; 
	}

	
	/**
	 * 删除被执行信息
	 * @param Long id
	 * @return Results
	 */
	public Results deleteById(Long id){

		sysDebtorDao.deleteById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改被执行信息
	 * @param sysDebtor
	 * @return Results
	 */
	public Results modify(SysDebtor sysDebtor){

		sysDebtorDao.update(sysDebtor);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询被执行信息
	 * @param sysDebtor
	 * @return Results
	 */
	public Results findById(Long id){

		SysDebtor sysDebtor = sysDebtorDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysDebtor);
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

		List<SysDebtor> list = sysDebtorDao.searchByPage(searchParams);
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("total", page.getTotalPage());
		rmap.put("page", page.getCurrentPage());
		rmap.put("records", page.getTotalCount());
		rmap.put("pageSize", page.getPageSize());
		rmap.put("rows", list);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),rmap);
		
		return results; 
	}

}
