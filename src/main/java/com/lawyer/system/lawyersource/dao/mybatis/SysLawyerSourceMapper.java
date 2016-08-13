package com.lawyer.system.lawyersource.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.lawyersource.domain.SysLawyerSource;

@MyBatisRepository
public interface SysLawyerSourceMapper {

  	/**
	 * save sys_lawyer_source
	 * @param SysLawyerSource
	 * @return 
	 */
	 public void save(SysLawyerSource sysLawyerSource);
	
	/**
	 * update sys_lawyer_source
	 * @param SysLawyerSource
	 * @return 
	 */
	 public void update(SysLawyerSource sysLawyerSource);
  	/**
	 * query sys_lawyer_source by primary key 
	 * @param Long id
	 * @return SysLawyerSource
	 */
	 public SysLawyerSource findById(Long id);
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<SysLawyerSource> searchByPage(Map<String,Object> searchParams);
	 
	 /**
	 * delete sys_lawyer_source by primary key 
	 * @param SysLawyerSource
	 * @return 
	 */
	 public void deleteById(Long id);
	 
}
