package com.lawyer.system.develop.service.serviceengine;

import java.util.Arrays;
import java.util.List;

import com.lawyer.system.develop.service.utils.DevelopContents;
import com.lawyer.system.develop.vo.Table;

public class ServiceElementKit {
	
	public ServiceElementKit(){}
	
	public ServiceElementKit(Table table,String packagePath){
		this.tableName = table.getName();
		this.tableCode = table.getCode();
		this.tableComment = table.getComment();
		this.packagePath = packagePath;
	}

	private String tableName;
	private String tableCode;
	private String tableComment;
	private String packagePath;

	public String getTemplatePath(){
		
		return DevelopContents.SERVICE_ENGINE_TEMPLATE_PATH;
		
	}

	public String getPackagePathBySplit() {
		return packagePath.replace(".", "/");
	}
	
	public String getPackagePath() {
		return packagePath;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}
	
	public String getTableFieldCode() {
		return dealBeanFieldCode(tableCode);
	}
	
	public String getTableClassCode() {
		return dealClassFieldCode(tableCode);
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	
	private String dealBeanFieldCode(String tableField){
		
		String rstr = dealClassFieldCode(tableField);
		rstr = rstr.substring(0, 1).toLowerCase()+rstr.substring(1);
		
		return rstr;
	}
	
	private String dealClassFieldCode(String tableField){
		
		StringBuilder sb=new StringBuilder();
		List<String> list = Arrays.asList(tableField.split("_"));
		for(int i=0;i<list.size();i++){
			String s=list.get(i);
			String ups=s.substring(0, 1).toUpperCase()+s.substring(1, s.length()).toLowerCase();
			sb.append(ups);
		}
		
		return sb.toString();
	}
	
}
