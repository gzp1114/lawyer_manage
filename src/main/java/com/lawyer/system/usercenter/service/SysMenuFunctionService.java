package com.lawyer.system.usercenter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawyer.cores.framework.mybatis.PageParameter;
import com.lawyer.cores.result.Results;
import com.lawyer.system.usercenter.dao.mybatis.SysMenuFunctionMapper;
import com.lawyer.system.usercenter.dao.mybatis.SysRoleMapper;
import com.lawyer.system.usercenter.domain.SysMenuFunction;
import com.lawyer.system.usercenter.domain.SysRole;

@Service("sysMenuFunctionService")
public class SysMenuFunctionService {
	
	private static Logger logger = LoggerFactory.getLogger(SysMenuFunctionService.class);
	
	@Autowired
	private SysMenuFunctionMapper sysMenuFunctionDao;
	@Autowired
	private SysRoleMapper sysRoleDao;

	
	/**
	 * 增加菜单功能
	 * @param sysMenuFunction
	 * @return Results
	 */
	public Results add(SysMenuFunction sysMenuFunction){
		
		sysMenuFunction.setCreateTime(new Date());
		if (sysMenuFunction.getParentId()==null) {
			sysMenuFunction.setParentId(0L);
		}
		sysMenuFunctionDao.save(sysMenuFunction);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		return results; 
	}

	
	/**
	 * 删除菜单功能,级联删除,删除该菜单以及所有子菜单
	 * @param Long id
	 * @return Results
	 */
	public Results deleteById(Long id){
		List<SysMenuFunction> menus = sysMenuFunctionDao.getChildByParentId(id);
		if(menus!=null&&menus.size()>0){
			for (int i = 0; i < menus.size(); i++) {
				deleteById(menus.get(i).getId());
			}
		}
		sysMenuFunctionDao.deleteById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 修改菜单功能
	 * @param sysMenuFunction
	 * @return Results
	 */
	public Results modify(SysMenuFunction sysMenuFunction){

		sysMenuFunctionDao.update(sysMenuFunction);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		return results; 
	}

	
	/**
	 * 查询菜单功能
	 * @param sysMenuFunction
	 * @return Results
	 */
	public Results findById(Long id){

		SysMenuFunction sysMenuFunction = sysMenuFunctionDao.findById(id);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysMenuFunction);
		return results; 
	}

	
	public Results findFirstMenuByUrl(String menuUrl){
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc());
		
		SysMenuFunction sysMenuFunction = sysMenuFunctionDao.findByUrl(menuUrl);
		if(sysMenuFunction == null){
			return results;
		}
		SysMenuFunction firstMenu = null;
		firstMenu = sysMenuFunctionDao.findById(sysMenuFunction.getParentId());
		while (true) {
			if(firstMenu.getMenuType() == 1){
				break;
			}
			firstMenu = sysMenuFunctionDao.findById(firstMenu.getParentId());
		}
		results.setData(firstMenu);
		
		return results; 
	}
	/**
	 * 根据条件校验URL是否重复
	 * @param searchParams
	 * @return
	 */
	public Long checkMenuUrl(Map<String,Object> searchParams){
		return sysMenuFunctionDao.checkMenuUrl(searchParams);
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

		List<SysMenuFunction> list = sysMenuFunctionDao.searchByPage(searchParams);
		Map rmap = new HashMap(8);
		rmap.put("total", page.getTotalPage());
		rmap.put("page", pageNumber);
		rmap.put("pageSize", pageSize);
		rmap.put("records", page.getTotalCount());
		
		String parentId = searchParams.get("parentId")==null?"":String.valueOf(searchParams.get("parentId"));
		String menuName = searchParams.get("menuName")==null?"":String.valueOf(searchParams.get("menuName"));
		String menuType = searchParams.get("menuType")==null?"":String.valueOf(searchParams.get("menuType"));
		
		rmap.put("parentId", parentId);
		rmap.put("menuName", menuName);
		rmap.put("menuType", menuType);
		
		rmap.put("rows", list);
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),rmap);
		
		return results; 
	}
	
	/**
	 * 查询菜单功能
	 * @param sysMenuFunction
	 * @return Results
	 */
	public Results getMenuTree(Long userSessionId){

		List<SysMenuFunction> sysMenuFunctions = sysMenuFunctionDao.getMenuTree(userSessionId);
		List<SysMenuFunction> tree = makeTree(sysMenuFunctions);
		StringBuffer html =  new StringBuffer();
		for (SysMenuFunction pracent : tree) {
			html.append("<li class='manager_tip manager_disc'>"); 
			html.append("<span>"+pracent.getMenuName()+"</span>");
			html.append("</li>");
			html.append("<li class='manager_list hidden'>");
			for (SysMenuFunction children : pracent.getChildren()) {
				html.append("<a href='/lawyer"+children.getMenuUrl()+"'>"+children.getMenuName()+"</a>");
			}
			html.append("</li>");
		}
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),html.toString());
		return results; 
	}
	
	/**
	 * 查询菜单权限功能
	 * @param sysMenuFunction
	 * @return Results
	 */
	public Results getAllTree(){

		List<SysMenuFunction> sysMenuFunctions = sysMenuFunctionDao.getAllTree();
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysMenuFunctions);
		return results; 
	}
	
	/**
	 * 查询菜单权限功能
	 * @param sysMenuFunction
	 * @return Results
	 */
	public Results getRoleTree(Long roleId){

		List<SysMenuFunction> sysMenuFunctions = sysMenuFunctionDao.getAllTree();
		SysRole sysRole = sysRoleDao.selectRoleMenuFunctions(roleId);
		if(sysRole!=null&&sysRole.getMenuFunctions()!=null&&sysRole.getMenuFunctions().size()>0){
			for (SysMenuFunction sysMenuFunction : sysMenuFunctions) {
				for (SysMenuFunction roleMenu : sysRole.getMenuFunctions()) {
					if(roleMenu.getId().equals(sysMenuFunction.getId())){
						sysMenuFunction.setUserHaved(true);
					}
				}
			}
		}
		
		Results results = new Results(
				UserCenterContents.API_RETURN_STATUS.NORMAL.value(),
				UserCenterContents.API_RETURN_STATUS.NORMAL.desc(),sysMenuFunctions);
		return results; 
	}
	
	private List<SysMenuFunction> makeTree(List<SysMenuFunction> list) {
		List<SysMenuFunction> parents = new ArrayList<SysMenuFunction>();
		for (SysMenuFunction e : list) {
			if(e.getParentId() == 0){
				e.setChildren(new ArrayList<SysMenuFunction>(0));
				parents.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parents);
		
		makeChildren(parents, list);
		
		return parents;
	}
	
	private void makeChildren(List<SysMenuFunction> parent, List<SysMenuFunction> children) {
		if (children.isEmpty() || parent.isEmpty()) {
			return ;
		}
		
		List<SysMenuFunction> tmp = new ArrayList<SysMenuFunction>();
		for (SysMenuFunction c1 : parent) {
			for (SysMenuFunction c2 : children) {
				c2.setChildren(new ArrayList<SysMenuFunction>(0));
				if (c1.getId().equals(c2.getParentId())) {
					c1.getChildren().add(c2);
					tmp.add(c2);
				}
			}
		}
		
		children.removeAll(tmp);
		
		makeChildren(tmp, children);
	}

}
