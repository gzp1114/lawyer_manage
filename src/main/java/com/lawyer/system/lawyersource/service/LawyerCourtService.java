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
import com.lawyer.system.lawyersource.dao.mybatis.LawyerCourtMapper;
import com.lawyer.system.lawyersource.domain.LawyerCourt;
import com.lawyer.system.usercenter.service.UserCenterContents;

@Service("lawyerCourtService")
public class LawyerCourtService {
	
	private static Logger logger = LoggerFactory.getLogger(LawyerCourtService.class);
	
	@Autowired
	private LawyerCourtMapper lawyerCourtDao;

	
	/**
	 * 增加法院信息
	 * @param lawyerCourt
	 * @return Results
	 */
	public Results add(LawyerCourt lawyerCourt){
		
		lawyerCourtDao.save(lawyerCourt);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		return results; 
	}

	
	/**
	 * 删除法院信息
	 * @param Long id
	 * @return Results
	 */
	public Results deleteById(Long id){

		lawyerCourtDao.deleteById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改法院信息
	 * @param lawyerCourt
	 * @return Results
	 */
	public Results modify(LawyerCourt lawyerCourt){

		lawyerCourtDao.update(lawyerCourt);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询法院信息
	 * @param lawyerCourt
	 * @return Results
	 */
	public Results findById(Long id){

		LawyerCourt lawyerCourt = lawyerCourtDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),lawyerCourt);
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

		List<LawyerCourt> list = lawyerCourtDao.searchByPage(searchParams);
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("total", page.getTotalPage());
		rmap.put("page", page.getCurrentPage());
		rmap.put("records", page.getTotalCount());
		
		rmap.put("rows", list);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),rmap);
		
		return results; 
	}
	
	public Results findProvinces(){
		List<LawyerCourt> list = lawyerCourtDao.findProvinces();
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),list);
		
		return results;
	}
	
	public Results findCourtsByPid(Long id){
		List<LawyerCourt> list = lawyerCourtDao.findByPid(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),list);
		
		return results;
	}

}
