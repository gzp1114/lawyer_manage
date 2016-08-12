package com.lawyer.system.lawyersource.dao.mybatis;
import java.util.List;
import java.util.Map;

import com.lawyer.cores.framework.mybatis.MyBatisRepository;
import com.lawyer.system.lawyersource.domain.SysAnnouncement;

@MyBatisRepository
public interface SysAnnouncementMapper {

  	/**
	 * save sys_announcement
	 * @param SysAnnouncement
	 * @return 
	 */
	 public void save(SysAnnouncement sysAnnouncement);
	
	/**
	 * update sys_announcement
	 * @param SysAnnouncement
	 * @return 
	 */
	 public void update(SysAnnouncement sysAnnouncement);
  	/**
	 * query sys_announcement by primary key 
	 * @param Long id
	 * @return SysAnnouncement
	 */
	 public SysAnnouncement findById(Long id); 
	 
	 /**
	  * 根据主体id获取被执行公告信息
	  * @param debtorCompanyId
	  * @return
	  */
	 public List<SysAnnouncement> findByCompanyid(Long debtorCompanyId);
	 
	 
	 
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	 List<SysAnnouncement> searchByPage(Map<String,Object> searchParams);
	 
	 /**
	 * delete sys_announcement by primary key 
	 * @param SysAnnouncement
	 * @return 
	 */
	 public void deleteById(Long id);
	 
}
