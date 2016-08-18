package com.lawyer.system.lawyersource.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.lawyersource.domain.SysContectEmail;

@MyBatisRepository
public interface SysContectEmailMapper {

  	/**
	 * save sys_contect_email
	 * @param SysContectEmail
	 * @return 
	 */
	 public void save(SysContectEmail sysContectEmail);
	
	/**
	 * update sys_contect_email
	 * @param SysContectEmail
	 * @return 
	 */
	 public void update(SysContectEmail sysContectEmail);
  	/**
	 * query sys_contect_email by primary key 
	 * @param Long id
	 * @return SysContectEmail
	 */
	 public SysContectEmail findById(Long id);
	 
	 /**
	  * 通过案源id获取邮件联系信息
	  * @param id
	  * @return
	  */
	 public List<SysContectEmail> findBySourceId(Long SourceId);
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<SysContectEmail> searchByPage(Map<String,Object> searchParams);
	 
	 /**
	 * delete sys_contect_email by primary key 
	 * @param SysContectEmail
	 * @return 
	 */
	 public void deleteById(Long id);
	 
}
