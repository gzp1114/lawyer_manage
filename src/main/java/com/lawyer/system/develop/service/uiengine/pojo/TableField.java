package com.lawyer.system.develop.service.uiengine.pojo;

public class TableField {
	
	public TableField(){}
	
	public TableField(String name,String code,String dataType){
		this.name = name;
		this.code = code;
		this.dataType = dataType;
	}
	
	/**
	 * 字段中文名称
	 */
	private String name;
	/**
	 * 字段英文名称
	 */
	private String code;
	/**
	 * 字段类型
	 */
	private String dataType;
	/**
	 * 字段长度
	 */
	private String length;
	/**
	 * 备注
	 */
	private String comment;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
