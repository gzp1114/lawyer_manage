package com.lawyer.system.lawyersource.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.lawyersource.domain.LawyerCourt;

@MyBatisRepository
public interface LawyerCourtMapper {

  	/**
	 * save lawyer_court
	 * @param LawyerCourt
	 * @return 
	 */
	 public void save(LawyerCourt lawyerCourt);
	
	/**
	 * update lawyer_court
	 * @param LawyerCourt
	 * @return 
	 */
	 public void update(LawyerCourt lawyerCourt);
  	/**
	 * query lawyer_court by primary key 
	 * @param Long Id
	 * @return LawyerCourt
	 */
	 public LawyerCourt findById(Long id);
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<LawyerCourt> searchByPage(Map<String,Object> searchParams);
	 
	 /**
	 * delete lawyer_court by primary key 
	 * @param LawyerCourt
	 * @return 
	 */
	 public void deleteById(Long id);
	 
	 /**
	  * 获取省级列表
	  * @return
	  */
	 List<LawyerCourt> findProvinces();
	 
	 /**
	  * 根据父节点获取法院列表
	  * @return
	  */
	 List<LawyerCourt> findByPid(Long id);
	 
}
