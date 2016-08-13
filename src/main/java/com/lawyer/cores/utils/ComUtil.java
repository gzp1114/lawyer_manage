package com.lawyer.cores.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class ComUtil {

	/**
     * 字符串中的汉字转化成16进制的ascii编码
     * @param s
     * @return
     */
    public static String str2HexAscii(String s) {
		String str = "";
		if (s == null || s.trim().equals(""))
			return str;
		for (int i = 0; i < s.length(); i++) {
			int ch;
			String s4;
			byte[] bytes = (String.valueOf(s.charAt(i))).getBytes();
			if (bytes.length == 1) { //英文字符不转化

				s4 = String.valueOf(s.charAt(i));
			} else {
				ch = (int) s.charAt(i);
				s4 = "\\u" + Integer.toHexString(ch);
			}
			str = str + s4;
		}
		return str;
	} 
    
    public static String getRequestPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}
	
	public static int getPageSize(String pageSize){
		return Integer.parseInt((pageSize == null || pageSize == "0") ? "20":pageSize); 
	}

	public static int getPageNo(String pageNo){
		return Integer.parseInt((pageNo == null || pageNo == "0") ? "1":pageNo); 
	}
	
	/**
	 * 接口调用返回状态码
	 */
	public enum API_RETURN_STATUS{
		PARAMETER_MD5_ERROR("-1","Enc signature error"),
		PARAMETER_TIME_STEMP_EXPIRE("-2","The request has expired");
		
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
	
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}
	
	public static String getCompanyName(String companyName){
		String newname = "";
		
		if(companyName.contains("有限公司")){
			int end = companyName.indexOf("有限公司");
			newname = companyName.substring(0, end)+"有限公司";
		}else if(companyName.contains("有限责任公司")){
			int end = companyName.indexOf("有限责任公司");
			newname = companyName.substring(0, end)+"有限责任公司";
		}else{
			newname = companyName;
		}
		
		return newname;
	}

}
