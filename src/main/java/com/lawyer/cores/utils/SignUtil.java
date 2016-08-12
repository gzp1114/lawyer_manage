package com.lawyer.cores.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.web.Servlets;

public class SignUtil {
	
	private static Logger logger = LoggerFactory.getLogger(SignUtil.class);
	
	/**
	 * 获取REST FULL签名
	 * @param request
	 * @return
	 */
	public static String getSign(HttpServletRequest request,String signKey) {
		
		String requestMethod = request.getMethod() + request.getRequestURI();
		
		logger.info("request method：" + requestMethod);
		logger.info("signKey：" + signKey);

		// 排序
		Map<String, Object> params = Servlets.getParametersStartingWith(request, "");
		params.remove("sign");
		
		List<String> list = new ArrayList<String>(params.keySet());
		Collections.sort(list);

		// 拼字符串
		StringBuffer strToVerfy = new StringBuffer();
		for (String key : list) {
			strToVerfy.append("&" + key + "=" + params.get(key));
		}
		String str = requestMethod + "?" + strToVerfy.substring(1)+signKey;
		
		logger.info("待签名串：" + str);

		// 签名
		String enc = MD5.getMD5(str.toString().getBytes()).toLowerCase();
		logger.info("enc：" + enc);
		
		return enc;
	}
}
