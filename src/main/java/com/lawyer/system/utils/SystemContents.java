package com.lawyer.system.utils;

public class SystemContents {
	
	/**
	 * 接口返回值正常状态代码
	 */
	public static String NORMAL = "0";
	
	/**
	 * 导出查询每次查询量
	 */
	public static int EXPORT_PAGE_SIZE = 1000;
	
	/**
	 * 依据类型及字典值取字典描述文本
	 * @param type
	 * @param value
	 * @return
	 */
	public static String getDicDesc(String type, int value) {
		
		String desc = "";
		
		if(type.equals("SYS_USER_SESSION_TYPE")) {
				desc = getsysUserSessionType(value);
		}else if(type.equals("SYS_USER_SESSION_STATUS")){
				desc = getsysUserSessionStatus(value);
		}else if(type.equals("SYS_APPLICATION_STATUS")){
				desc = getsysApplicationStatus(value);
		}else if(type.equals("SYS_DICTIONARY_VALUE_STATUS")){
				desc = getsysDictionaryValueStatus(value);
		}else if(type.equals("SYS_MENU_FUNCTION_MENU_TYPE")){
				desc = getsysMenuFunctionMenuType(value);
		}else if(type.equals("SYS_MENU_FUNCTION_IS_VALID")){
				desc = getsysMenuFunctionIsValid(value);
		}else if(type.equals("SYS_TENANT_STATUS")){
				desc = getsysTenantStatus(value);
		}else if(type.equals("SYS_TENANT_TYPE")){
				desc = getsysTenantType(value);
		}else if(type.equals("SYS_TENANT_APP_STATUS")){
				desc = getsysTenantAppStatus(value);
		}else if(type.equals("SYS_TOOL_TYPE")){
			desc = getsysToolType(value);
		}else if(type.equals("SYS_TOOL_STATUS")){
			desc = getsysToolStatus(value);
		}else if(type.equals("SYS_USER_INFO_GENDER")){
				desc = getsysUserInfoGender(value);
		}
		
		
		return desc;
	}

	/**
	 * 接口调用返回状态码
	 */
	public enum API_RETURN_STATUS {
		SERVER_SESSION_ERROR("-2", "用户登陆失效，请重新登陆"), 
		SERVER_INTERNAL_ERROR("-1", "服务器内部错误"), 
		NORMAL("0", "正常"), 
		PARA_ERROR("1", "参数传递错误"), 
		PARAMETER_MD5_ERROR("2","请求参数签名错误"), 
		;

		private String value;
		private String desc;

		API_RETURN_STATUS(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 定义字典：
	 * 用户状态->类型
	 * 类型：0为Admin管理员、1为平台管理员、2为租户管理员、3为租户用户
	 * @author koko zu
	 */
	public enum SYS_USER_SESSION_TYPE{
		ADMIN_ADMINISTRATOR(0, "Admin管理员"), 
		PLATFORM_ADMINISTRATOR(1, "平台管理员"), 
		TENANT_ADMINISTRATOR(2, "租户管理员"), 
		TENANT_USER(3, "租户用户");
		
		private int value;
		private String desc;

		SYS_USER_SESSION_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 定义字典：
	 * 用户状态->状态
	 * 状态，值：1为正常、2为停用、3为删除
	 * @author koko zu
	 */
	public enum SYS_USER_SESSION_STATUS{
		NORMAL(1, "正常"), 
		STOP(2, "停用"), 
		DELETE(3, "删除");
		
		private int value;
		private String desc;

		SYS_USER_SESSION_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 取字典描述：
	 * 用户状态->类型
	 * @param value
	 * @return
	 */
	public static String getsysUserSessionType(int value) {

		for (SYS_USER_SESSION_TYPE dic : SYS_USER_SESSION_TYPE.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";

	}
	
	/**
	 * 取字典描述：
	 * 用户状态->状态
	 * @param value
	 * @return
	 */
	public static String getsysUserSessionStatus(int value) {

		for (SYS_USER_SESSION_STATUS dic : SYS_USER_SESSION_STATUS.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";

	}
	
	/**
	 * 定义字典：
	 * 应用接入管理->状态
	 * 状态，值：1为启用，2为停用
	 * @author koko zu
	 */
	public enum SYS_APPLICATION_STATUS{
		ENABLE(1, "启用"), 
		STOP(2, "停用");
		
		private int value;
		private String desc;

		SYS_APPLICATION_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 取字典描述：
	 * 应用接入管理->类型
	 * @param value
	 * @return
	 */
	public static String getsysApplicationStatus(int value) {

		for (SYS_APPLICATION_STATUS dic : SYS_APPLICATION_STATUS.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}
	
	/**
	 * 定义字典：
	 * 字典值->状态
	 * 状态，值：1为启用，2为停用
	 * @author koko zu
	 */
	public enum SYS_DICTIONARY_VALUE_STATUS{
		ENABLE(1, "启用"), 
		STOP(2, "停用");
		
		private int value;
		private String desc;

		SYS_DICTIONARY_VALUE_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 取字典描述：
	 * 字典值->类型
	 * @param value
	 * @return
	 */
	public static String getsysDictionaryValueStatus(int value) {

		for (SYS_DICTIONARY_VALUE_STATUS dic : SYS_DICTIONARY_VALUE_STATUS.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}
	
	/**
	 * 定义字典：
	 * 菜单功能表->类型
	 * 类型，值：1为模块，2为菜单，3为功能
	 * @author koko zu
	 */
	public enum SYS_MENU_FUNCTION_MENU_TYPE{
		MODULE(1, "模块"), 
		MENU(2, "菜单"),
		FUNCTION(3, "功能");
		
		private int value;
		private String desc;

		SYS_MENU_FUNCTION_MENU_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 取字典描述：
	 * 菜单功能表->类型
	 * @param value
	 * @return
	 */
	public static String getsysMenuFunctionMenuType(int value) {

		for (SYS_MENU_FUNCTION_MENU_TYPE dic : SYS_MENU_FUNCTION_MENU_TYPE.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}
	
	/**
	 * 定义字典：
	 * 菜单功能表->是否有效
	 * 是否有效，值：0为无效，1为有效
	 * @author koko zu
	 */
	public enum SYS_MENU_FUNCTION_IS_VALID{
		INVALID(0, "无效"), 
		VALID(1, "有效");
		
		private int value;
		private String desc;

		SYS_MENU_FUNCTION_IS_VALID(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 取字典描述：
	 * 菜单功能表->是否有效
	 * @param value
	 * @return
	 */
	public static String getsysMenuFunctionIsValid(int value) {

		for (SYS_MENU_FUNCTION_IS_VALID dic : SYS_MENU_FUNCTION_IS_VALID.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}
	
	/**
	 * 定义字典：
	 * 租户表->状态
	 * 状态，值：1为正常、2为停用
	 * @author koko zu
	 */
	public enum SYS_TENANT_STATUS{
		NORMAL(1, "正常"), 
		DISABLE(2, "停用");
		
		private int value;
		private String desc;

		SYS_TENANT_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 取字典描述：
	 * 租户表->状态
	 * @param value
	 * @return
	 */
	public static String getsysTenantStatus(int value) {

		for (SYS_TENANT_STATUS dic : SYS_TENANT_STATUS.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}
	
	/**
	 * 定义字典：
	 * 租户表->类型
	 * 类型，值：1为实体租户、2为虚拟租户（不产生业务数据）
	 * @author koko zu
	 */
	public enum SYS_TENANT_TYPE{
		ENTITY_TENANT(1, "实体租户"), 
		VIRTUAL_TENANT(2, "虚拟租户");
		
		private int value;
		private String desc;

		SYS_TENANT_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 取字典描述：
	 * 租户表->类型
	 * @param value
	 * @return
	 */
	public static String getsysTenantType(int value) {

		for (SYS_TENANT_TYPE dic : SYS_TENANT_TYPE.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}
	
	/**
	 * 定义字典：
	 * 应用租户接入管理->状态
	 * 状态，值：1为启用，2为停用
	 * @author koko zu
	 */
	public enum SYS_TENANT_APP_STATUS{
		ENABLE(1, "启用"), 
		STOP(2, "停用");
		
		private int value;
		private String desc;

		SYS_TENANT_APP_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 取字典描述：
	 * 应用租户接入管理->状态
	 * @param value
	 * @return
	 */
	public static String getsysTenantAppStatus(int value) {

		for (SYS_TENANT_APP_STATUS dic : SYS_TENANT_APP_STATUS.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}
	
	/**
	 * 定义字典：
	 * 工具->类型
	 * 状态，值：1ldap，2为数据库
	 * @author koko zu
	 */
	public enum SYS_TOOL_TYPE{
		LDAP(1, "ldap"), 
		DATA_BASE(2, "数据库");
		
		private int value;
		private String desc;

		SYS_TOOL_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}
	
	/**
	 * 定义字典：
	 * 工具->状态
	 * 状态，值：1正常，2无效
	 * @author SixSir
	 */
	public enum SYS_TOOL_STATUS{
		NORMAL(1, "正常"),
		INVALID(2, "无效");
		
		private int value;
		private String desc;

		SYS_TOOL_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}
	
	/**
	 * 取字典描述：
	 * 工具->类型
	 * @param value
	 * @return
	 */
	public static String getsysToolType(int value) {

		for (SYS_TOOL_TYPE dic : SYS_TOOL_TYPE.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}
	/**
	 * 取字典描述：
	 * 工具->状态
	 * @param value
	 * @return
	 */
	public static String getsysToolStatus(int value) {

		for (SYS_TOOL_STATUS dic : SYS_TOOL_STATUS.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}

	/**
	 * 定义字典：
	 * 用户基本信息表->性别
	 * 性别，值：1为男性、2为女性、3为其它
	 * @author koko zu
	 */
	public enum SYS_USER_INFO_GENDER{
		MALE(1, "男性"), 
		WOMAN(2, "女性"),
		OTHER(3, "其他");
		
		private int value;
		private String desc;

		SYS_USER_INFO_GENDER(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer value() {
			return this.value;
		}

		public String desc() {
			return this.desc;
		}
	}

	/**
	 * 取字典描述：
	 * 用户基本信息表->性别
	 * @param value
	 * @return
	 */
	public static String getsysUserInfoGender(int value) {

		for (SYS_USER_INFO_GENDER dic : SYS_USER_INFO_GENDER.values()) {
			if (dic.value() == value){
				return dic.desc();
			}
		}
		return "";
	}

}