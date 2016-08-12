package com.lawyer.system.lawyersource.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.lawyersource.domain.Court;
import com.lawyer.system.lawyersource.domain.SysDebtor;

@MyBatisRepository
public interface SysDebtorMapper {

  	/**
	 * save sys_debtor
	 * @param SysDebtor
	 * @return 
	 */
	 public void save(SysDebtor sysDebtor);
	 
	 /**
	 * 批量插入被执行信息
	 * @param SysDebtor
	 * @return 
	 */
	 public void saveMore(List<SysDebtor> list);
	
	/**
	 * update sys_debtor
	 * @param SysDebtor
	 * @return 
	 */
	 public void update(SysDebtor sysDebtor);
  	/**
	 * query sys_debtor by primary key 
	 * @param Long id
	 * @return SysDebtor
	 */
	 public SysDebtor findById(Long id);
	 
	 /**
	  * 根据主体id获取被执行信息
	  * @param debtorCompanyId
	  * @return
	  */
	 public List<SysDebtor> findByCompanyid(Long debtorCompanyId);
	 
	 /**
	  * 根据主体id和案号获取数据
	  * @return
	  */
	 public List<SysDebtor> findByCidCasecode(Map<String,Object> searchParams);
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<SysDebtor> searchByPage(Map<String,Object> searchParams);
	 
	 /**
	 * delete sys_debtor by primary key 
	 * @param SysDebtor
	 * @return 
	 */
	 public void deleteById(Long id);
	 
}
