package com.lawyer.system.develop.service;

import java.io.File;
import java.util.List;

import com.lawyer.system.develop.service.controllerengine.ControllerAssemblyEngine;
import com.lawyer.system.develop.service.controllerengine.ControllerElementKit;
import com.lawyer.system.develop.service.daoengine.AssemblyEngine;
import com.lawyer.system.develop.service.serviceengine.ServiceAssemblyEngine;
import com.lawyer.system.develop.service.serviceengine.ServiceElementKit;
import com.lawyer.system.develop.service.uiengine.UiAssemblyEngine;
import com.lawyer.system.develop.service.uiengine.UiElementKit;
import com.lawyer.system.develop.service.utils.ZipCompressor;
import com.lawyer.system.develop.vo.Table;

public class EngineContext {

	/**
	 * 参数说明   
	 * @param List<Table> tbList ：需要生成代码的表列表
	 * @param String packagePath ：包路径，如：com.ariadnethread.uop.system.usercenter
	 * @param String powerdesignerFile ：powerdesigner文件（包括路径及文件名）（建议放在工程下的fileResource/developCode）
	 * @param String destPath ：存放目标文件的路径（建议放在工程下的fileResource/developCode）
	 * @return String zip ：返回zip文件路径（在destPath目录中的zip文件）
	 */
	public static String engine(List<Table> tbList,String packagePath,String powerdesignerFile,String destPath){
		
		String folderStr = "sourceCode-"+System.currentTimeMillis();
		
		String sourceFolder = destPath+File.separator+folderStr;
		String uiFolder  = sourceFolder+File.separator+"UI";
		String conFolder = sourceFolder+File.separator+"controller"; 
		String svFolder  = sourceFolder+File.separator+"service"; 
		String daoFolder  = sourceFolder+File.separator+"dao"+File.separator+"mybatis";
		String daoDomainFolder  = sourceFolder+File.separator+"dao"+File.separator+"domain";
		
		createFolder(sourceFolder,uiFolder,conFolder,svFolder,daoFolder,daoDomainFolder);
		
		for(Table table : tbList){
			//生成controller
			ControllerElementKit controllerElementKit = new ControllerElementKit(table,packagePath);
			ControllerAssemblyEngine.createController(controllerElementKit, conFolder);
			
			//生成dao
			AssemblyEngine.createBean(powerdesignerFile, daoDomainFolder,table.getCode(), packagePath);   
			AssemblyEngine.createDao(powerdesignerFile, daoFolder,table.getCode(), packagePath);
			AssemblyEngine.createDaoXML(powerdesignerFile, daoFolder,table.getCode(), packagePath);
			
			//生成service
			ServiceElementKit serviceElementKit = new ServiceElementKit(table,packagePath);
			ServiceAssemblyEngine.createService(serviceElementKit, svFolder);
			
			//生成UI
			UiElementKit uiElementKit = new UiElementKit(table);
			UiAssemblyEngine.createUiSearch(uiElementKit, uiFolder);
			UiAssemblyEngine.createUiAdd(uiElementKit, uiFolder);
			UiAssemblyEngine.createUiModify(uiElementKit, uiFolder);
		}
		
		String sourceZipFile = sourceFolder+".zip";
		ZipCompressor zcv = new ZipCompressor(sourceZipFile);     
		zcv.compress(sourceFolder);
		
		return sourceZipFile;
	}
	
	private static void createFolder(String sourceFolder,String uiFolder,String conFolder,String svFolder,String daoFolder,String daoDomainFolder){
		
		File folder = new File(sourceFolder);
		folder.mkdirs();
		folder = new File(uiFolder);
		folder.mkdirs();
		folder = new File(conFolder);
		folder.mkdirs();
		folder = new File(svFolder);
		folder.mkdirs();
		folder = new File(daoFolder);
		folder.mkdirs();
		folder = new File(daoDomainFolder);
		folder.mkdirs();
		
	}
}
