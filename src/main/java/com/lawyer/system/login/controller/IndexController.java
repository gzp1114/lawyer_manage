
package com.lawyer.system.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawyer.cores.framework.spring.RequestMappingName;


@Controller
//@RequestMapping("/index")
public class IndexController {
	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	/**
	 * <p>跳转到index页面</p>
	 * 
	 */
	@RequestMappingName(value = "跳转到index页面")
	@RequestMapping(value = "toIndex", method = RequestMethod.GET)
	public String index(Model model) {
		return "/index/index";
	}
	
	/**
	 * <p>跳转到menu页面</p>
	 * 
	 */
	@RequestMappingName(value = "跳转到menu页面")
	@RequestMapping(value = "toMenu", method = RequestMethod.GET)
	public String menu(Model model) {
//		model.addAttribute("userName", LoginUtil.getCurrentUserName());
		return "index/menu";
	}
	
	/**
	 * <p>跳转到userInfo页面</p>
	 * 
	 */
	@RequestMappingName(value = "跳转到userInfo页面")
	@RequestMapping(value = "toUserInfo", method = RequestMethod.GET)
	public String userInfo(Model model) {
//		model.addAttribute("userName", LoginUtil.getCurrentUserName());
		return "index/userInfo";
	}
	
}
