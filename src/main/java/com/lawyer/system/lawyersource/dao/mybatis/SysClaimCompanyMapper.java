package com.lawyer.system.lawyersource.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.lawyersource.domain.SysClaimCompany;

@MyBatisRepository
public interface SysClaimCompanyMapper {

  	/**
	 * save sys_claim_company
	 * @param SysClaimCompany
	 * @return 
	 */
	 public void save(SysClaimCompany sysClaimCompany);
	
	/**
	 * update sys_claim_company
	 * @param SysClaimCompany
	 * @return 
	 */
	 public void update(SysClaimCompany sysClaimCompany);
  	/**
	 * query sys_claim_company by primary key 
	 * @param Long id
	 * @return SysClaimCompany
	 */
	 public SysClaimCompany findById(Long id);
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<SysClaimCompany> searchByPage(Map<String,Object> searchParams);
	 
	 /**
	 * delete sys_claim_company by primary key 
	 * @param SysClaimCompany
	 * @return 
	 */
	 public void deleteById(Long id);
	 
}
