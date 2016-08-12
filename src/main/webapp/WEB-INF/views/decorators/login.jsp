<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title><sitemesh:title/></title>
	
    <link type="text/css" rel="stylesheet" href="${ctx}/static/tools/login/css/reset.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/static/tools/login/css/style.css">
    <script type="text/javascript" src="${ctx}/static/threadui/js/jquery-1.11.3.js"></script>
	<sitemesh:head/>
	
</head>

<body>
	<sitemesh:body/>
</body>
</html>
