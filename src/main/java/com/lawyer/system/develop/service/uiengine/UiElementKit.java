package com.lawyer.system.develop.service.uiengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lawyer.system.develop.service.uiengine.pojo.TableField;
import com.lawyer.system.develop.service.utils.DevelopContents;
import com.lawyer.system.develop.vo.Column;
import com.lawyer.system.develop.vo.Table;

public class UiElementKit {
	
	public UiElementKit(){}
	
	private String tableName;
	private String tableCode;
	private String tableComment;
	
	private List<TableField> fieldList;
	
	public UiElementKit(Table table){
		this.tableName = table.getName();
		this.tableCode = table.getCode();
		this.tableComment = table.getComment();
		dealTalbeField(table.getColumns());
	}
	
	private void dealTalbeField(List<Column> columnList){
		fieldList = new ArrayList();
		for(Column column : columnList){
			fieldList.add(new TableField(column.getName(),dealBeanFieldCode(column.getCode()),column.getDataType()));
		}
	}
	
	//'用户姓名','用户昵称', '出生日期',
	public String getTableRowTitle(){
		
		 StringBuffer str = new StringBuffer("");
		 for(TableField tfd : fieldList){
			 str.append("\""+tfd.getName()+"\",\r\n\t\t\t\t\t\t");
		 }
		return str.toString();
		
	}
	
	//{name:'userName',width:100, sortable:false},
	public String getTableRowField(){
		
		StringBuffer str = new StringBuffer("");
		for(TableField tfd : fieldList){
			str.append("{name:'"+tfd.getCode()+"',width:100, sortable:false},\r\n\t\t\t\t\t");
		}
		return str.toString();
		
	}
	
	//{"search_userName":$("#search_userName").val(),"search_nickname":$("#search_nickname").val()}
	public String getSearchPostData(){
		
		StringBuffer str = new StringBuffer("");
		for(TableField tfd : fieldList){
			str.append("\"search_"+tfd.getCode()+"\":$(\"#search_"+tfd.getCode()+"\").val(),");
		}
		String rstr = "{";
		if(str.substring(str.length()-1).equals(",")){
			String str1 = str.substring(0, str.length()-1);
			rstr = rstr+str1;
		}
		rstr = rstr+"}";
		return rstr;
		
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

	public String getTemplatePath(){
		
		return DevelopContents.UI_ENGINE_TEMPLATE_PATH;
		
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

	public String getTableName() {
		
		return this.tableName;
	}
	
	public List<TableField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<TableField> fieldList) {
		this.fieldList = fieldList;
	}
	
	

	
}
