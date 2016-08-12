package com.lawyer.system.utils.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lawyer.cores.result.Results;
import com.lawyer.system.usercenter.domain.SysMenuFunction;
import com.lawyer.system.usercenter.domain.SysUserSession;
import com.lawyer.system.usercenter.service.SysMenuFunctionService;
import com.lawyer.system.utils.appdatasource.CustomerContextHolder;

/**
 * @author tfj
 * 2014-8-1
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
	
	@Autowired
	private SysMenuFunctionService sysMenuFunctionService;
	
    /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
//    	if ("GET".equalsIgnoreCase(request.getMethod())) {
//    		
//        }
        log.info("==============执行顺序: 1、preHandle================");  
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
      
        log.info("requestUri:"+requestUri);  
        log.info("contextPath:"+contextPath);  
        log.info("url:"+url);  
        log.info("dddddddddddddddddddddddddddddddddddddddddddddddddd:"); 
        
        String appId=request.getParameter("appId");
        String businessTenantId=request.getParameter("tenantId");
        String menuFunctionId=request.getParameter("functionId");
        log.info("appId==:"+appId); 
        log.info("businessTenantId==:"+businessTenantId); 
        log.info("menuFunctionId==:"+menuFunctionId); 
        
        
//        CustomerContextHolder.setCustomerType(appId);
//        CustomerContextHolder.setCustomerType(businessTenantId+""+menuFunctionId);
        
//        if(url.equals("/sysUserInfo/toSearch")){
//    	if(url.equals("/saleCategory/toSearch")){
//    		log.info("url.equals(\"/saleCategory/toSearch\"");
//        	CustomerContextHolder.setCustomerType("ciasSaleBaseDSTest");
//        }
        
        
        /* */
        
        if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;  
		    Method method = handlerMethod.getMethod(); 
		    if (method.isAnnotationPresent(RequiresPermissions.class)) {
		    	RequiresPermissions myAnnotation = method
	                    .getAnnotation(RequiresPermissions.class);
		    	String[] value = myAnnotation.value();
		    	url = value[0];
		    }
		}else{
			return true;
		}
		
		log.info("url:"+url); 
        
        SysUserSession user =  (SysUserSession) request.getSession().getAttribute("sysUser"); 
        Results results = sysMenuFunctionService.findFirstMenuByUrl(url);
        SysMenuFunction sysMenuFunction = (SysMenuFunction) results.getData();
        
        if(user != null && sysMenuFunction != null){
	        Long functionId = sysMenuFunction.getId();
	        
	        log.info("functionId="+functionId);  
	        
//	        CustomerContextHolder.setCustomerType(tenantId+""+functionId);
        }else{
        	log.info("user != null && sysMenuFunction != null");  
        }
       
        log.info("preHandlebbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb=");  
        
//        if(sysMenuFunction != null){
//        	String dbType = user.getId()+ "" + sysMenuFunction.getId();
//            DynamicDataSource dataSource = new DynamicDataSource();
//            dataSource.selectDataSource(dbType);
//        }
        
        return true;   
    }  
  
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作   
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {   
    	
    	CustomerContextHolder.setCustomerType("defaultTargetDataSource");
        log.info("==============执行顺序: 2、postHandle================");  
    }  
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等  
     *  
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
       
        //DynamicDataSource dataSource = new DynamicDataSource();
        //dataSource.selectDataSource("11");
//        log.info("==============执行顺序: 3、afterCompletion 切换为默认数据源================");  
    }  

}  

