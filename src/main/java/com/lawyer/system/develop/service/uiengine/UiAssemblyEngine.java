package com.lawyer.system.develop.service.uiengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.system.develop.service.daoengine.TableFieldKit;
import com.lawyer.system.develop.service.utils.DevelopContents;


public class UiAssemblyEngine {

	private static Logger logger = LoggerFactory.getLogger(UiAssemblyEngine.class);
	
	/**
	  * 生成前端查询列表文件
	  */
	  public static void createUiSearch(UiElementKit uiElementKit,String destPath){
		  UiEngineService uiEngineService=new UiEngineService();
		  uiEngineService.engine(uiElementKit, destPath, DevelopContents.UI_ENGINE_TEMPLATE_PATH+"engine_search.vm", uiElementKit.getTableFieldCode()+".jsp");
	  }
	  /**
	   * 生成前端新增文件
	   */
	  public static void createUiAdd(UiElementKit uiElementKit,String destPath){
		  UiEngineService uiEngineService=new UiEngineService();
		  uiEngineService.engine(uiElementKit, destPath, DevelopContents.UI_ENGINE_TEMPLATE_PATH+"engine_add.vm", uiElementKit.getTableFieldCode()+"Add.jsp");
	  }
	  
	  /**
	   * 生成前端修改文件
	   */
	  public static void createUiModify(UiElementKit uiElementKit,String destPath){
		  UiEngineService uiEngineService=new UiEngineService();
		  uiEngineService.engine(uiElementKit, destPath, DevelopContents.UI_ENGINE_TEMPLATE_PATH+"engine_modify.vm", uiElementKit.getTableFieldCode()+"Modify.jsp");
	  }
  
}
