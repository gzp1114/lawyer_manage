<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%
	 String path = request.getContextPath();  
	 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
	 Object sysUser = request.getSession().getAttribute("sysUser");
	 String username=(String)request.getSession().getAttribute("username");
	 String lastLoginTime=(String)request.getSession().getAttribute("lastLoginTime");
 %>

<div class="cinema_maipinquan">管理平台</div>
<a href="javascript:if(confirm('确认要退出吗？'))window.location='${ctx}/logout'" class="cinema_icon"></a>
<div class="cinema_logo"></div>
<div class="cinema_exit">
   <p> 您好!<span class="cinema_name"><%=username%></span></p>
    <h1>上次登录时间:<span><%=lastLoginTime%></span></h1>
</div>
