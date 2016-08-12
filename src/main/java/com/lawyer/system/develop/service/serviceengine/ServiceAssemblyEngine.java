package com.lawyer.system.develop.service.serviceengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.system.develop.service.utils.DevelopContents;


public class ServiceAssemblyEngine {

	private static Logger logger = LoggerFactory.getLogger(ServiceAssemblyEngine.class);
	
	/**
	  * 生成service
	  */
	  public static void createService(ServiceElementKit serviceElementKit,String destPath){
		  ServiceEngineService serviceEngineService=new ServiceEngineService();
		  serviceEngineService.engine(serviceElementKit, destPath, DevelopContents.SERVICE_ENGINE_TEMPLATE_PATH+"engine_service.vm", serviceElementKit.getTableClassCode()+"Service.java");
	  }
  
}
