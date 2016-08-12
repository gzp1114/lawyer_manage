package com.lawyer.system.usercenter.service;


public class UserCenterContents {

	
	/**
	 * 接口调用返回状态码
	 */
	public enum API_RETURN_STATUS{
		SERVER_INTERNAL_ERROR("-1","服务器内部错误"),
		NORMAL("0","正常"),
		PARA_ERROR("1","参数传递错误"),
		PARAMETER_MD5_ERROR("3","parameter md5 encode error"),
		SESSION_TIMEOUT("4","session过期"),
		;
		
		private String value;
		private String desc;
		API_RETURN_STATUS(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		public String value(){
			return this.value;
		}
		public String desc(){
			return this.desc;
		}
	}
}