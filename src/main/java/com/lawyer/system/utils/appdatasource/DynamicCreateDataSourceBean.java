package com.lawyer.system.utils.appdatasource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lawyer.cores.tools.other.PropertiesUtil;

public class DynamicCreateDataSourceBean implements ApplicationContextAware,ApplicationListener{
	
	private Logger logger = Logger.getLogger(this.getClass());

	private ConfigurableApplicationContext app;  
	  
    private JdbcTemplate jdbcTemplate;  
      
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)  
    {  
        this.jdbcTemplate = jdbcTemplate;  
    }  
  
    private DynamicDataSource dynamicDataSource;  
      
    public void setDynamicDataSource(DynamicDataSource dynamicDataSource) {  
        this.dynamicDataSource = dynamicDataSource;  
    }  
  
    public void setApplicationContext(ApplicationContext app) throws BeansException {  
        this.app = (ConfigurableApplicationContext)app;  
    }  
  
      
    public void onApplicationEvent(ApplicationEvent event) {  
        // 如果是容器刷新事件OR Start Event  
        if (event instanceof ContextRefreshedEvent) {  
            try {  
                regDynamicBean();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            //System.out.println(event.getClass().getSimpleName()+" 事件已发生！");  
        }  
          
    }  
  
    private void regDynamicBean() throws IOException {  
        // 解析属性文件，得到数据源Map  
        Map<String, DataSourceInfo> mapCustom = parseTenantAppCon();  
//        Map<String, DataSourceInfo> mapCustom = parsePropertiesFile();  
        // 把数据源bean注册到容器中  
       addSourceBeanToApp(mapCustom);  
        
    }
    
    private Map<String, DataSourceInfo> parseTenantAppCon(){
    	
        String sql = "SELECT business_tenant_id,menu_function_id,business_jdbc_driver,business_jdbc_url,business_jdbc_username,business_jdbc_password FROM sys_tenant_app WHERE status = 1";
        
        List list = jdbcTemplate.queryForList(sql); 
        Iterator iterator = list.iterator();
        Map<String, DataSourceInfo> map = new HashMap<String, DataSourceInfo>();
        
        while (iterator.hasNext()) {
        	
            Map tamap = (Map) iterator.next();
            
            String connUrl = "jdbc:mysql://"+(String)tamap.get("business_jdbc_url")+"?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
            String userName = (String)tamap.get("business_jdbc_username");
            String password = (String)tamap.get("business_jdbc_password");
            
            DataSourceInfo dsi = new DataSourceInfo();
        	dsi.connUrl = connUrl;
        	dsi.userName = userName;
        	dsi.password = password;
        	
        	Integer businessTenantId = (Integer)tamap.get("business_tenant_id");
        	Integer menuFunctionId = (Integer)tamap.get("menu_function_id");
        	
        	logger.info("connUrl="+connUrl);
        	logger.info("userName="+userName);
        	logger.info("password="+password);
        	logger.info("businessTenantId="+businessTenantId);
        	logger.info("menuFunctionId="+menuFunctionId);
        	
        	String dataSourceKey = businessTenantId+""+menuFunctionId;
            map.put(dataSourceKey, dsi);
//            map.put("ciasSaleBaseDSTest", dsi);
        }
        
        logger.info("parseTenantAppCon map.size="+map.size());

        return map;
    }
    
    private Map<String, DataSourceInfo> parsePropertiesFile(){
    	
    	Map<String, DataSourceInfo> map = new HashMap<String, DataSourceInfo>();
    	DataSourceInfo dsi = new DataSourceInfo();
    	PropertiesUtil.getPropByKey("jdbc.url.sale");
    	dsi.connUrl=PropertiesUtil.getPropByKey("jdbc.url.sale");
    	dsi.userName=PropertiesUtil.getPropByKey("jdbc.username.sale");
    	dsi.password=PropertiesUtil.getPropByKey("jdbc.password.sale");
    	map.put("ciasSaleBaseDSTest", dsi);
    	
    	return map;
    }
  
    /** 
     * 功能说明：根据DataSource创建bean并注册到容器中 
     *  
     * @param acf 
     * @param mapCustom 
     */  
    private void addSourceBeanToApp(Map<String, DataSourceInfo> mapCustom) {  
        DefaultListableBeanFactory acf = (DefaultListableBeanFactory) app  
                .getAutowireCapableBeanFactory();  
  
        String DATASOURCE_BEAN_CLASS = "org.apache.tomcat.jdbc.pool.DataSource";  
        BeanDefinitionBuilder bdb;  
          
        Iterator<String> iter = mapCustom.keySet().iterator();  
          
        Map<Object,Object> targetDataSources = new LinkedHashMap<Object, Object>();  
          
//        baseBeanComm = new ChildBeanDefinition("dataSource");  
        //  将默认数据源放入 targetDataSources map中  
        targetDataSources.put("dataSourceA", app.getBean("dataSource"));  
  
        // 根据数据源得到数据，动态创建数据源bean 并将bean注册到applicationContext中去  
        while (iter.hasNext()) {  
              
            //  bean ID  
            String beanKey = iter.next();  
            //  创建bean  
            bdb = BeanDefinitionBuilder.rootBeanDefinition(DATASOURCE_BEAN_CLASS);  
            bdb.getBeanDefinition().setAttribute("id", beanKey);  
            bdb.addPropertyValue("driverClassName", "com.mysql.jdbc.Driver");  
            bdb.addPropertyValue("url", mapCustom.get(beanKey).connUrl);  
            bdb.addPropertyValue("username", mapCustom.get(beanKey).userName);  
            bdb.addPropertyValue("password", mapCustom.get(beanKey).password);  
            bdb.addPropertyValue("maxActive", 50);  
            bdb.addPropertyValue("maxIdle", 10);  
            bdb.addPropertyValue("minIdle", 0);  
            bdb.addPropertyValue("defaultAutoCommit", false);  
            //  注册bean  
            acf.registerBeanDefinition(beanKey, bdb.getBeanDefinition());  
              
            //  放入map中，注意一定是刚才创建bean对象  
            targetDataSources.put(beanKey, app.getBean(beanKey));  
              
        }  
        //  将创建的map对象set到 targetDataSources；  
        dynamicDataSource.setTargetDataSources(targetDataSources); 
          
        //  必须执行此操作，才会重新初始化AbstractRoutingDataSource 中的 resolvedDataSources，也只有这样，动态切换才会起效  
        dynamicDataSource.afterPropertiesSet();  
          
    }  
  
    //  自定义数据结构  
    private class DataSourceInfo{    
  
        public String connUrl;    
        public String userName;    
        public String password;    
            
        public String toString() {  
            return "(url:" + connUrl + ", username:" + userName + ", password:"  
                + password + ")";  
        }   
    }    
  
}
