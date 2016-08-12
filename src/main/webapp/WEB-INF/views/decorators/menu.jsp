<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.lawyer.system.usercenter.domain.*"%>
<%
 //List<SysMenuFunction> menutree = (List<SysMenuFunction>)request.getSession().getAttribute("menutree");
	String menutree = (String)request.getSession().getAttribute("menutree");
 %>
<ul id="menu">
	<%=menutree%> 
	
    <%-- <li class="manager_tip">
        <span class="manager_down">用户中心</span>
    </li>
    <li class="manager_list">
    	<a href="${ctx}/sysUser/toSearch">用户管理</a>
        <a href="${ctx}/sysMenuFunction/toSearch">菜单管理</a>
        <a href="${ctx}/sysRole/toSearch?rows=10" >角色管理</a>
    </li>
    <li class="manager_tip manager_disc">
        <span>集成开发环境</span>
    </li>
    <li class="manager_list hidden">
        <a href="${ctx}/autoDevelop/toCodeBuild">代码构建</a>
    </li>  --%>
</ul>

<script type="text/javascript">
	
</script>
