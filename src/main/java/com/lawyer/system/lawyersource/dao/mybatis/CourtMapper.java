package com.lawyer.system.lawyersource.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.lawyersource.domain.Court;
import com.lawyer.system.lawyersource.domain.SysDebtor;

@MyBatisRepository
public interface CourtMapper {

  	/**
	 * query sys_debtor by primary key 
	 * @param Long id
	 * @return SysDebtor
	 */
	 public SysDebtor findById(Long id);
	 
	 /**
	 * delete sys_debtor by primary key 
	 * @param SysDebtor
	 * @return 
	 */
	 public void deleteById(Long id);
	 
	 /**
	  * 获取XXX条数据
	  * @return
	  */
	 public List<Court> findByCount(Long count);
	 
	 
	 /**
	 * 删除xxx条数据
	 * @param SysDebtor
	 * @return 
	 */
	 public void deleteByCount(Long count);
	 
}
