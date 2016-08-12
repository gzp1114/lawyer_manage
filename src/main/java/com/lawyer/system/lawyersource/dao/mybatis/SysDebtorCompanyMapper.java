package com.lawyer.system.lawyersource.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.lawyersource.domain.SysDebtorCompany;

@MyBatisRepository
public interface SysDebtorCompanyMapper {

  	/**
	 * save sys_debtor_company
	 * @param SysDebtorCompany
	 * @return 
	 */
	 public void save(SysDebtorCompany sysDebtorCompany);
	
	/**
	 * update sys_debtor_company
	 * @param SysDebtorCompany
	 * @return 
	 */
	 public void update(SysDebtorCompany sysDebtorCompany);
  	/**
	 * query sys_debtor_company by primary key 
	 * @param  
	 * @return SysDebtorCompany
	 */
	 public SysDebtorCompany findById(Long id );
	 
	 /**
	  * 根据名称获取公司信息 
	  * @param id
	  * @return
	  */
	 public SysDebtorCompany findByName(String name );
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<SysDebtorCompany> searchByPage(Map<String,Object> searchParams);
	 
	 /**
	 * delete sys_debtor_company by primary key 
	 * @param SysDebtorCompany
	 * @return 
	 */
	 public void deleteById( Long id);
	 
}
