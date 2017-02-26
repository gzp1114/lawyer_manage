<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ include file="/static/common/default.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><sitemesh:title/></title>
	<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/favicon.ico" media="screen" /> 

    <link type="text/css"  rel="stylesheet" href="${ctx}/static/threadui/css/reset.css">
    <link type="text/css"  rel="stylesheet" href="${ctx}/static/threadui/css/style.css">   
   	<%-- <link rel="stylesheet" href="${ctx}/static/threadui/css/select_xinjianmaipinquan2.css" /> --%>
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="${ctx}/static/threadui/datejs/daterangepicker-bs3.css" />
  
    <script type="text/javascript" src="${ctx}/static/threadui/js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${ctx}/static/threadui/js/style.js"></script>
	<script type="text/javascript" src="${ctx}/static/tools/click-menu.js"></script>
	<script type="text/javascript" src="${ctx}/static/tools/page.js"></script>

    <!--日历控件-->
    <script type="text/javascript" src="${ctx}/static/threadui/datejs/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/threadui/datejs/jquery-ui-1.7.1.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/threadui/datejs/moment.js"></script>
    <script type="text/javascript" src="${ctx}/static/threadui/datejs/daterangepicker.js"></script>
    
    <sitemesh:head/>
</head>
<body>
<div class="manager_content">
	 <decorator:getProperty property="page.content1"/>
    <!--头部开始-->
    <div class="cinema_top clearfix">
	    <%@ include file="/WEB-INF/views/decorators/header.jsp"%>
	</div>
    <!--头部结束-->
    
    <div class="manager_content">
        <!--左侧开始-->
        <div class="manager_left">
            <%@ include file="/WEB-INF/views/decorators/menu.jsp"%>
        </div>
        <!--左侧结束-->
        
        <!--右侧开始-->
        <div class="manager_right">
        	<sitemesh:body/>
        	
        	<p class="manager_system">债转管理平台&nbsp;&nbsp;CopyRight2013&nbsp;&nbsp;技术支持</p>
        </div>
        <!--右侧结束-->
    </div>
</div>
</body>
</html>