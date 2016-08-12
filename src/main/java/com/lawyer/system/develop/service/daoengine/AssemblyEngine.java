package com.lawyer.system.develop.service.daoengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.system.develop.service.utils.DevelopContents;


public class AssemblyEngine {

	private static Logger logger = LoggerFactory.getLogger(AssemblyEngine.class);
	
	/**
	  *生成xml文件
	  */
	  public static void createDaoXML(String powerdesignerFile,String destPath,String tableName,String packagePath){
		  DaoEngineService daoEngineService=new DaoEngineService();
		  daoEngineService.engine(powerdesignerFile,destPath,
				  DevelopContents.DAO_ENGINE_TEMPLATE_PATH+"engine.vm",new TableFieldKit().tableName(tableName)+"Mapper.xml",tableName,packagePath);
	  }
  
	  /**
	   *生成bean
	   */
	  public static void createBean(String powerdesignerFile,String destPath,String tableName,String packagePath){
		  DaoEngineService daoEngineService=new DaoEngineService();
		  daoEngineService.engine(powerdesignerFile,destPath,
				  DevelopContents.DAO_ENGINE_TEMPLATE_PATH+"engine_bean.vm",new TableFieldKit().tableName(tableName)+".java",tableName,packagePath);
	  }
  
	  /**
	   *生成dao
	   */
	  public static void createDao(String powerdesignerFile,String destPath,String tableName,String packagePath){
		DaoEngineService daoEngineService=new DaoEngineService();
		daoEngineService.engine(powerdesignerFile,destPath,
				DevelopContents.DAO_ENGINE_TEMPLATE_PATH+"engine_dao.vm",new TableFieldKit().tableName(tableName)+"Mapper.java",tableName,packagePath);
	  }
}
