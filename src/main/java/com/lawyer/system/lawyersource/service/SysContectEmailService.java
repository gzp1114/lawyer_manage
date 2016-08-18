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
import com.lawyer.system.lawyersource.dao.mybatis.SysContectEmailMapper;
import com.lawyer.system.lawyersource.domain.SysContectEmail;
import com.lawyer.system.usercenter.domain.SysUserSession;
import com.lawyer.system.usercenter.service.UserCenterContents;

@Service("sysContectEmailService")
public class SysContectEmailService {
	
	private static Logger logger = LoggerFactory.getLogger(SysContectEmailService.class);
	
	@Autowired
	private SysContectEmailMapper sysContectEmailDao;

	
	/**
	 * 增加联系邮件
	 * @param sysContectEmail
	 * @return Results
	 */
	public Results add(SysContectEmail sysContectEmail){
		SysUserSession user = (SysUserSession) SecurityUtils.getSubject().getSession().getAttribute("sysUser");
		
		sysContectEmail.setCreatetime(new Date());
		sysContectEmail.setOperatorId(user.getId());
		sysContectEmailDao.save(sysContectEmail);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		return results; 
	}

	
	/**
	 * 删除联系邮件
	 * @param Long id
	 * @return Results
	 */
	public Results deleteById(Long id){

		sysContectEmailDao.deleteById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改联系邮件
	 * @param sysContectEmail
	 * @return Results
	 */
	public Results modify(SysContectEmail sysContectEmail){

		sysContectEmailDao.update(sysContectEmail);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询联系邮件
	 * @param sysContectEmail
	 * @return Results
	 */
	public Results findById(Long id){

		SysContectEmail sysContectEmail = sysContectEmailDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysContectEmail);
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

		List<SysContectEmail> list = sysContectEmailDao.searchByPage(searchParams);
		
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
