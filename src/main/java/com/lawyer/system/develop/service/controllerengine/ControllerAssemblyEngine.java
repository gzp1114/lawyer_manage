package com.lawyer.system.develop.service.controllerengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.system.develop.service.utils.DevelopContents;


public class ControllerAssemblyEngine {

	private static Logger logger = LoggerFactory.getLogger(ControllerAssemblyEngine.class);
	
	/**
	  * 生成controller
	  */
	  public static void createController(ControllerElementKit controllerElementKit,String destPath){
		  ControllerEngineService controllerEngineService=new ControllerEngineService();
		  controllerEngineService.engine(controllerElementKit, destPath, DevelopContents.CONTROLLER_ENGINE_TEMPLATE_PATH+"engine_controller.vm", controllerElementKit.getTableClassCode()+"Controller.java");
	  }
  
}
